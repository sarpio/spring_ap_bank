package com.example.demo.rest;

import com.example.demo.rest.dto.AccountDTO;
import com.example.demo.services.AccountServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {

    private final AccountServices accountServices;

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        return ResponseEntity.ok(accountServices.findAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable UUID id) {
        return ResponseEntity.ok(accountServices.findAccountById(id));
    }

    @PostMapping("/")
    public ResponseEntity<String> createAccount(@Validated @RequestBody AccountDTO dto) {
        return ResponseEntity.ok(accountServices.createAccount(dto));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAccountById(UUID id) {
        String response = accountServices.deleteAccountById(id);
        return ResponseEntity.ok(response);
    }
}
