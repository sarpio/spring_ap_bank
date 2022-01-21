package com.example.demo.services;

import com.example.demo.rest.dto.OperationDTO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("operation-service")
public interface OperationFeignClient {

    @GetMapping(value = "/api/operation/{accountId}", consumes = "application/json")
    List<OperationDTO> getAllCustomerAccountsByAccountId(@PathVariable("accountId") Long id);

    @PostMapping(value = "/api/operation", consumes = "application/json")
    OperationDTO saveOperation(@RequestBody OperationDTO dto);

}
