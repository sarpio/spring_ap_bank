package com.example.demo.services;

import com.example.demo.rest.dto.OperationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("operation-service")
public interface OperationFeignClient {

    @GetMapping(value = "/api/operation/{accountId}", consumes = "application/json")
    List<OperationDTO> getAllCustomerOperationByAccountId(@PathVariable("accountId") Long id);

}
