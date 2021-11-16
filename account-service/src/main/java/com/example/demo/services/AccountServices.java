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
        for (AccountDTO dto : accountsDTO) {

        }
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

    public String createAccount(AccountDTO dto, Currency currency) {
        dto.setCurrency(currency);
        accountRepository.save(EntityDtoMapper.map(dto));
        return "Account created for user: " + dto.getCustomerId();
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
}