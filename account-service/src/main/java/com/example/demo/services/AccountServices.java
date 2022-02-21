package com.example.demo.services;

import com.example.demo.entity.Account;
import com.example.demo.repo.AccountCache;
import com.example.demo.repo.AccountRepository;
import com.example.demo.rest.dto.AccountDTO;
import com.example.demo.rest.dto.OperationDTO;
import com.example.demo.until.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServices {

    private final AccountRepository accountRepository;
    private final OperationFeignClient operationFeignClient;
    private final AccountCache accountCache;
    private final CustomerFeignClient customerFeignClient;

    public List<AccountDTO> findAllAccounts() {
        List<AccountDTO> accountsDTO = accountRepository
                .findAll()
                .stream()
                .map(EntityDtoMapper::map)
                .collect(Collectors.toList());
        for (AccountDTO dto : accountsDTO) {
            dto.setOperations(operationFeignClient
                    .getAllCustomerOperationByAccountId(dto.getId()));
        }
        return accountsDTO;
    }

    public AccountDTO findAccountById(Long id) {
        AccountDTO accountDTO = accountCache.getAccount(id)
                .orElseGet(() -> accountRepository
                        .findById(id)
                        .map(EntityDtoMapper::map)
                        .orElseThrow(
                                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Provided Account Id not exists")
                        ));
        List<OperationDTO> operationDTOS = operationFeignClient.getAllCustomerOperationByAccountId(id);
        accountCache.saveAccountInCache(accountDTO);
        accountDTO.setOperations(operationDTOS);
        return accountDTO;
    }

    public AccountDTO createAccount(AccountDTO dto) {
        dto.setBalance(0.0);
        Account entity = EntityDtoMapper.map(dto);
        checkCustomer(dto);
        try {
            accountRepository.save(entity);
            accountCache.saveAccountInCache(EntityDtoMapper.map(entity));
            return EntityDtoMapper.map(entity);
        } catch (Exception ex) {
            ex.printStackTrace();
            dto.setAccountNumber();
            System.err.print("Detected duplicated number or number is missing. Account number generated automatically");
            Account save = accountRepository.save(EntityDtoMapper.map(dto));
            AccountDTO accountDTO = EntityDtoMapper.map(save);
            accountCache.saveAccountInCache(accountDTO);
            return accountDTO;
        }

    }

    private void checkCustomer(AccountDTO dto) {
        try {
            customerFeignClient.getCustomerById(dto.getCustomerId());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Customer with id " + dto.getCustomerId() + " is not exist");

        }
    }

    public String deleteAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cannot remove account because given id not exists"));
        if (account.getBalance() != 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cannot remove account if balance in not 0");
        }
        accountCache.deleteAccountFromCache(id);
        accountRepository.deleteById(id);
        return "Account removed";
    }

    public List<AccountDTO> findByCustomerId(Long customerId) {
        List<Account> accounts = accountRepository.findByCustomerId(customerId);
        if (accounts.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found customer with id: " + customerId);
        }
        List<AccountDTO> accountDTOS = accounts.stream()
                .map(EntityDtoMapper::map)
                .collect(Collectors.toList());
        for (AccountDTO dto : accountDTOS) {
            dto.setOperations(operationFeignClient.getAllCustomerOperationByAccountId(dto.getId()));
            accountCache.saveAccountInCache(dto);
        }
        return accountDTOS;

    }
}