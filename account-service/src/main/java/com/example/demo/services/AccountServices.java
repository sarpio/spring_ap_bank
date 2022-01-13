package com.example.demo.services;

import com.example.demo.entity.Account;
import com.example.demo.entity.Currency;
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
    private final OperationWebService operationWebService;
    private final AccountCache accountCache;

    public List<AccountDTO> findAllAccounts() {
        List<AccountDTO> accountsDTO = accountRepository
                .findAll()
                .stream()
                .map(EntityDtoMapper::map)
                .collect(Collectors.toList());
        for (AccountDTO dto : accountsDTO) {
            dto.setOperations(operationWebService
                    .getAllCustomerAccountsByAccountId(dto.getId())
                    .blockFirst());
        }
        return accountsDTO;
    }

    public AccountDTO findAccountById(Long id) {
        AccountDTO accountDTO = accountCache.getAccount(id).orElseGet(() -> accountRepository
                .findById(id)
                .map(EntityDtoMapper::map)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Provided id not exists")
                ));
        List<OperationDTO> operationDTOS = operationWebService.getAllCustomerAccountsByAccountId(id).blockFirst();
        accountCache.saveAccountInCache(accountDTO);
        accountDTO.setOperations(operationDTOS);
        return accountDTO;
    }

    public AccountDTO createAccount(AccountDTO dto, Currency currency) {
        dto.setCurrency(currency);
        if (isNumberExists(dto.getAccountNumber()) || dto.getAccountNumber() == null) {
            dto.setAccountNumber(setAccountNumber());
            System.err.println("Detected duplicated number or number is missing. Account number generated automatically");
        }
        Account entity = EntityDtoMapper.map(dto);
        entity.setBalance(0.0);
        accountRepository.save(entity);
        accountCache.saveAccountInCache(EntityDtoMapper.map(entity));
        return EntityDtoMapper.map(entity);
    }

    public String deleteAccountById(Long id) {
        boolean present = accountRepository.findById(id).isPresent();
        if (present) {
            accountCache.deleteAccountFromCache(id);
            accountRepository.deleteById(id);
            return "Account removed";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot remove account because given id not exists");
        }
    }

    public List<AccountDTO> findByCustomerId(Long customerId) {
        if (accountCache.getAccountByCustomerId(customerId).isEmpty()) {
            List<Account> accounts = accountRepository.findByCustomerId(customerId);
            if (accounts.size() == 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found customer with given id");
            }
            List<AccountDTO> accountDTOS = accounts.stream().map(EntityDtoMapper::map).collect(Collectors.toList());
            for (AccountDTO dto : accountDTOS) {
                dto.setOperations(operationWebService.getAllCustomerAccountsByAccountId(dto.getId()).blockFirst());
            }
            return accountDTOS;
        } else {
            return accountCache.getAccountByCustomerId(customerId);
        }

    }

    public Long setAccountNumber() {
        return (long) ((Math.random() * (9999 - 1000)) + 1000);
    }

    private boolean isNumberExists(Long number) {
        Account byAccountNumber = accountRepository.findByAccountNumber(number);
        return byAccountNumber != null;
    }

    public void updateBalanceByAccountId(AccountDTO dto) {
        if (accountRepository.existsById(dto.getId())) {
            Account account = EntityDtoMapper.map(dto);
            accountRepository.save(account);
            accountCache.saveAccountInCache(dto);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with given id not exists");
        }
    }
}