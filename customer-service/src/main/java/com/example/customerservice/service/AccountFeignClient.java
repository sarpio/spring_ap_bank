package com.example.customerservice.service;

import com.example.customerservice.rest.dto.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@FeignClient("account-service")
public interface AccountFeignClient {

    @GetMapping(value = "/api/account/customer/{id}", consumes = "application/json")
    List<AccountDTO> getCustomerAccounts(@PathVariable("id") Long id);

    @PostMapping(value = "/api/account", consumes = "application/json")
    AccountDTO createNewAccount(@RequestBody @Valid AccountDTO dto);
}
