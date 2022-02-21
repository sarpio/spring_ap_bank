package com.example.customerservice.service;

import com.example.customerservice.entity.Customer;
import com.example.customerservice.repo.CustomerCache;
import com.example.customerservice.repo.CustomerRepository;
import com.example.customerservice.rest.dto.AccountDTO;
import com.example.customerservice.rest.dto.CustomerDTO;
import com.example.customerservice.util.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerCache customerCache;
    private final AccountFeignClient accountFeignClient;

    public List<CustomerDTO> findAll() {
        List<CustomerDTO> customersDTO = customerRepository
                .findAll()
                .stream()
                .map(EntityDtoMapper::map)
                .collect(Collectors.toList());
        for (CustomerDTO dto : customersDTO) {
            try {
                dto.setAccounts(accountFeignClient.getCustomerAccounts(dto.getId()));
            } catch (Exception e) {
                dto.setAccounts(new ArrayList<>());
            }
        }
        return customersDTO;
    }

    public CustomerDTO findById(Long id) {
        CustomerDTO customerDTO = customerCache.getCustomer(id)
                .orElseGet(() -> customerRepository
                        .findById(id)
                        .map(EntityDtoMapper::map)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found ID: " + id)));
        try {
            List<AccountDTO> accountDTOS = accountFeignClient.getCustomerAccounts(id);
            customerDTO.setAccounts(accountDTOS);
        } catch (Exception ex) {
            customerDTO.setAccounts(new ArrayList<>());
        }
        customerCache.saveCustomerInCache(customerDTO);
        return customerDTO;
    }

    public CustomerDTO createCustomer(CustomerDTO dto) {

        try{
            Customer customer = EntityDtoMapper.map(dto);
            Customer save = customerRepository.save(customer);
            customerCache.saveCustomerInCache(EntityDtoMapper.map(save));
            for(AccountDTO accountDTO : dto.getAccounts()){
                accountDTO.setCustomerId(save.getId());
                accountFeignClient.createNewAccount(accountDTO);
            }
                CustomerDTO customerDTO = EntityDtoMapper.map(save);
                List<AccountDTO> accountDTOS = accountFeignClient.getCustomerAccounts(customerDTO.getId());
                customerDTO.setAccounts(accountDTOS);
                return customerDTO;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Duplicate customer name");
        }
    }

//    public CustomerDTOPost createNewCustomer(CustomerDTOPost postDto) {
//        CustomerDTO customerDTO = CustomerDTO.builder().build();
//        AccountDTO accountDTO = AccountDTO.builder().build();
//        try {
//            customerDTO.setName(postDto.getName());
//            Customer customer = EntityDtoMapper.map(customerDTO);
//            customerRepository.findByName(customerDTO.getName())
//                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//            customerRepository.save(customer);
//            accountDTO.setCustomerId(customer.getId());
//            accountDTO.setAccountNumber(postDto.getAccountNumber());
//            accountDTO.setCurrency(postDto.getCurrency());
//            accountFeignClient.createNewAccount(accountDTO);
//        } catch (ResponseStatusException ex) {
//            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Duplicated Customer name", ex);
//        }
//        return postDto;
//    }

    public String deleteCustomerById(Long id) {
        if (customerRepository.existsById(id)) {
            customerCache.deleteCustomerFromCache(id);
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
        customerCache.saveCustomerInCache(EntityDtoMapper.map(entity));
        return EntityDtoMapper.map(entity);
    }
}
