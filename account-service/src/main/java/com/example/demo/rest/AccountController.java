package com.example.demo.rest;

import com.example.demo.entity.Currency;
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
@RequestMapping("/api/account/")
public class AccountController {

    private final AccountServices accountServices;

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        return ResponseEntity.ok(accountServices.findAllAccounts());
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountServices.findAccountById(id));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<AccountDTO>>getAccountByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(accountServices.findByCustomerId(customerId));
    }

    @PostMapping
    public ResponseEntity<String> createAccount(@Validated @RequestBody AccountDTO dto,
                                                @Validated @RequestParam Currency currency) {
        return ResponseEntity.ok(accountServices.createAccount(dto, currency));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAccountById(@RequestParam("id") Long id) {
        String response = accountServices.deleteAccountById(id);
        return ResponseEntity.ok(response);
    }
}
