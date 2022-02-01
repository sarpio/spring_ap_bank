package com.example.customerservice.rest;

import com.example.customerservice.rest.dto.CustomerDTO;
import com.example.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
@CrossOrigin("*")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody @Valid CustomerDTO dto) {
        return ResponseEntity.ok(customerService.createCustomer(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customerService.deleteCustomerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO>updateCustomerById(@PathVariable Long id, @RequestBody @Valid CustomerDTO dto) {
        return ResponseEntity.ok(customerService.updateCustomer(id, dto));
    }
}
