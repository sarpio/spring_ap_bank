package com.example.demo.services;

import com.example.demo.entity.Account;
import com.example.demo.entity.Currency;
import com.example.demo.repo.AccountRepository;
import com.example.demo.rest.dto.AccountDTO;
import com.example.demo.until.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServices {

    private final AccountRepository accountRepository;

    public List<AccountDTO> findAllAccounts() {
        List<AccountDTO> accountsDTO = accountRepository
                .findAll()
                .stream()
                .map(EntityDtoMapper::map)
                .collect(Collectors.toList());
        return accountsDTO;
    }

    public AccountDTO findAccountById(Long id) {
        return accountRepository
                .findById(id)
                .map(EntityDtoMapper::map)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
    }

    public AccountDTO createAccount(AccountDTO dto, Currency currency) {
        dto.setCurrency(currency);
       if (isNumberExists(dto.getAccountNumber())) {
           dto.setAccountNumber(setAccountNumber());
           System.err.println("Detected duplicated number. Account number generated automatically");
       }
        Account entity = EntityDtoMapper.map(dto);
        entity.setBalance(0.0);
        accountRepository.save(entity);
        return EntityDtoMapper.map(entity);
    }

    public String deleteAccountById(Long id) {
        boolean present = accountRepository.findById(id).isPresent();
        if (present) {
            accountRepository.deleteById(id);
            return "Account removed";
        } else {
            return new ResponseStatusException(HttpStatus.NOT_FOUND).toString();
        }
    }

    public List<AccountDTO> findByCustomerId(Long customerId) {
        List<Account> accounts = accountRepository.findByCustomerId(customerId);
        return accounts.stream().map(EntityDtoMapper::map).collect(Collectors.toList());
    }

    public String setAccountNumber() {
        Random random = new Random();
        String accountNumber = String.format("%02d", random.nextInt(100));
        for (int i = 0; i < 5; i++) {
            accountNumber = accountNumber + "-" + String.format("%04d", random.nextInt(10000));
        }
        return accountNumber;
    }

    private boolean isNumberExists(String number) {
        Account byAccountNumber = accountRepository.findByAccountNumber(number);
        if (byAccountNumber != null) {
            return true;
        }
        return false;
    }
}