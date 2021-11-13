package com.example.demo.rest.dto;

import com.example.demo.entity.Type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Random;
import java.util.UUID;

@Data
@Builder
public class AccountDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;
    private String accountNumber;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID customerId;
    private boolean isForeign;
    private Type type;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long balance;

    public UUID getCustomerId() {
        return UUID.randomUUID();
    }

    public String getAccountNumber() {
        Random random = new Random();
        String accountNumber = String.format("%02d", random.nextInt(100));
        for (int i = 0; i < 5; i++) {
            accountNumber = accountNumber + "-" + String.format("%04d", random.nextInt(10000));
        }
//        return String.format("%04d", random.nextInt(10000));
        return accountNumber;
    }
}
