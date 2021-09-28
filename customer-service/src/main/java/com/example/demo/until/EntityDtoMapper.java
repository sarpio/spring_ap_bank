package com.example.demo.until;

import com.example.demo.entity.Customer;
import com.example.demo.rest.dto.CustomerDTO;
import org.springframework.beans.BeanUtils;

public class EntityDtoMapper {
    public static CustomerDTO mappedToClientDTO(Customer customer) {
        CustomerDTO customerDTO = CustomerDTO.builder().build();
        BeanUtils.copyProperties(customer, customerDTO);
//        if (customer.getBankId() != null) {
//            customerDTO.setBankDTO(EntityDtoMapper.mapToDto(customer.getBankId().));
//        }
        return customerDTO;
    }
}
