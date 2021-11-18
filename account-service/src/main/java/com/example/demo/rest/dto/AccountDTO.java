package com.example.demo.rest.dto;

import com.example.demo.entity.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.Random;

@Data
@Builder
public class AccountDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String accountNumber;
    private Long customerId;
    @Value("false")
    private Integer isForeign;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Currency currency;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double balance;

}
