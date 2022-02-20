package com.example.demo.rest;

import com.example.demo.rest.dto.AccountDTO;
import com.example.demo.services.AccountServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
@CrossOrigin("*")
public class AccountController {

    private final AccountServices accountServices;

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        return ResponseEntity.ok(accountServices.findAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountServices.findAccountById(id));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<AccountDTO>> getAccountByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(accountServices.findByCustomerId(customerId));
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody AccountDTO dto) {
        return ResponseEntity.ok(accountServices.createAccount(dto));
        //TODO Sprawdzi czy customer już istnieje taki user_id
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable("id") Long id) {
        String response = accountServices.deleteAccountById(id);
        return ResponseEntity.ok(response);
    }
}