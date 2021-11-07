package com.example.demo.services;

import com.example.demo.repo.AccountRepository;
import com.example.demo.rest.dto.AccountDTO;
import com.example.demo.until.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServices {

    private final AccountRepository accountRepository;

    public List<AccountDTO> findAllAccounts() {
        return accountRepository
                .findAll()
                .stream()
                .map(EntityDtoMapper::map)
                .collect(Collectors.toList());
    }

    public AccountDTO findAccountById(UUID id) {
        return accountRepository
                .findById(id)
                .map(EntityDtoMapper::map)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
    }

    public String createAccount(AccountDTO dto) {
        accountRepository.save(EntityDtoMapper.map(dto));
        return "Account created for user: " + dto.getCustomerId();
    }

    public String deleteAccountById(UUID id) {
        if (accountRepository.findById(id).isPresent()) {
            accountRepository.deleteById(id);
            return "Account removed";
        } else {
            return new ResponseStatusException(HttpStatus.NOT_FOUND).toString();
        }
    }

    private String accountNumberGenerator() {
        Random random = new Random();
        return String.format("%09d", random.nextInt(1000000000));
    }
}