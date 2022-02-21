package com.example.demo.services;

import com.example.demo.rest.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("customer-service")
public interface CustomerFeignClient {
    @GetMapping(value = "/api/customer/{id}", consumes = "application/json")
    CustomerDTO getCustomerById(@PathVariable("id") Long id);
}
