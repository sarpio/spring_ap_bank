package com.example.demo.services;

import com.example.demo.rest.dto.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("account-service")
public interface AccountFeignClient {

    @GetMapping(value = "/api/account/{id}", consumes = "application/json")
    AccountDTO getAccountById(@PathVariable("id") Long id);

    @GetMapping(value = "/api/account/customer/{id}", consumes = "application/json")
    List<AccountDTO> getCustomerAccounts(@PathVariable("id") Long id);

    @PostMapping(value = "/api/account", consumes = "application/json")
    AccountDTO saveNewAccountForCustomer(@RequestBody AccountDTO dto);

    @PutMapping(value = "/api/account/{id}", consumes = "application/json")
    AccountDTO putAccount(@PathVariable("id") Long id,@RequestBody AccountDTO dto);
}
