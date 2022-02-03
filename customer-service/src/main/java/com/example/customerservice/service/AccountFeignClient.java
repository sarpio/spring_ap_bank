package com.example.customerservice.service;

import com.example.customerservice.rest.dto.AccountDTO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("account-service")
public interface AccountFeignClient {

    @GetMapping(value = "/api/account/customer/{id}", consumes = "application/json")
    List<AccountDTO> getCustomerAccounts(@PathVariable("id") Long id);
}
