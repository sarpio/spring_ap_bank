package com.example.demo.util;

import com.example.demo.entity.Operation;
import com.example.demo.rest.dto.OperationDTO;
import org.springframework.beans.BeanUtils;

public class EntityDtoMapper {

    public static OperationDTO map(Operation entity) {
        OperationDTO dto = OperationDTO.builder().build();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static Operation map(OperationDTO dto) {
        Operation entity = Operation.builder().build();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
