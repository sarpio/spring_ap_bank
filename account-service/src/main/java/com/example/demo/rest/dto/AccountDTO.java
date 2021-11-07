package com.example.demo.rest.dto;

import com.example.demo.entity.Type;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AccountDTO {

    private UUID id;
    private long accountNumber;
    private UUID customerId;
    private boolean isForeign;
    private Type type;
}
