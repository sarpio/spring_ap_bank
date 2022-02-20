package com.example.demo.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Currency;

@Data
@Builder
public class AccountDTO {

    private Long id;
    private Long accountNumber;
    private Long customerId;
    private Integer isForeign;
    private Currency currency;
    private Double balance;

}
