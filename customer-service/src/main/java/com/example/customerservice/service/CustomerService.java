package com.example.customerservice.service;

import com.example.customerservice.entity.Customer;
import com.example.customerservice.repo.CustomerRepository;
import com.example.customerservice.rest.dto.AccountDTO;
import com.example.customerservice.rest.dto.CustomerDTO;
import com.example.customerservice.util.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountWebService accountWebService;

    public List<CustomerDTO> findAll() {
        List<CustomerDTO> customersDTO = customerRepository
                .findAll()
                .stream()
                .map(EntityDtoMapper::map)
                .collect(Collectors.toList());
        for (CustomerDTO dto : customersDTO) {
            dto.setAccounts(accountWebService.getAllCustomerAccounts(dto.getId()).blockFirst());
        }
        return customersDTO;
    }

    public CustomerDTO findById(Long id) {
        Customer customer = customerRepository
                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found ID: " + id));
        List<AccountDTO> accountDTOS = accountWebService.getAllCustomerAccounts(id).blockFirst();
        CustomerDTO customerDTO = EntityDtoMapper.map(customer);
        customerDTO.setAccounts(accountDTOS);
        return customerDTO;
    }

    public CustomerDTO createCustomer(CustomerDTO dto) {
        Customer customer = EntityDtoMapper.map(dto);
        Customer save = customerRepository.save(customer);
        Long customerId = save.getId();
        return EntityDtoMapper.map(save);
    }

    public String deleteCustomerById(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return String.format("Customer with ID:%s has been deleted", id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO dto) {
        Customer entity = EntityDtoMapper.map(dto);
        entity.setId(id);
        customerRepository.save(entity);
        return EntityDtoMapper.map(entity);
    }
}
