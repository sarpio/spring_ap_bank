package com.example.demo.rest.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Currency;

@Data
@Builder
public class AccountDTO {

    @Positive
    private Long id;
    @Min(1000)@Max(9999)
    private Long accountNumber;
    @NotNull
    @Min(1)
    private Long customerId;
    @Min(0)
    @Max(1)
    @NotNull
    private Integer isForeign;
    private Currency currency;
    private Double balance;

}
