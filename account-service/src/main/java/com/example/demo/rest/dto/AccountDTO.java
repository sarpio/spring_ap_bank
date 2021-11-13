package com.example.demo.rest.dto;

import com.example.demo.entity.Currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Value;

import java.util.Random;
import java.util.UUID;

@Data
@Builder
public class AccountDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String accountNumber;


    private Long customerId;
    @Value("false")
    private boolean isForeign;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Currency currency;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long balance;


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
