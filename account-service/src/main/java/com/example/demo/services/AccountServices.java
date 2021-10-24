package com.example.demo.services;

import com.example.demo.rest.dto.AccountDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountServices {
    public List<AccountDTO> findAllAccounts() {
        return null;
    }

    public AccountDTO findAccountById(UUID id) {
        return null;
    }

    public AccountDTO createAccount(AccountDTO dto) {
        return null;
    }

    public String deleteAccountById(UUID id) {
        return "Account removed";
    }
}
