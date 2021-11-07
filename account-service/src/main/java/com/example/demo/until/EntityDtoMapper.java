package com.example.demo.until;

import com.example.demo.entity.Account;
import com.example.demo.rest.dto.AccountDTO;
import org.springframework.beans.BeanUtils;

public class EntityDtoMapper {

    public static Account map(AccountDTO dto) {
        Account entity = Account.builder().build();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public static AccountDTO map(Account entity) {
        AccountDTO dto = AccountDTO.builder().build();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
