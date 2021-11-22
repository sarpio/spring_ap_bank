package com.example.customerservice.util;

import com.example.customerservice.entity.Customer;
import com.example.customerservice.rest.dto.CustomerDTO;
import org.springframework.beans.BeanUtils;

public class EntityDtoMapper {

    public static CustomerDTO map(Customer entity) {
        CustomerDTO dto = CustomerDTO.builder().build();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static  Customer map(CustomerDTO dto) {
        Customer entity = Customer.builder().build();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

}
