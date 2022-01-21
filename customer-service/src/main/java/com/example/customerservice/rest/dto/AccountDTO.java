package com.example.customerservice.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountDTO {

    @JsonIgnoreProperties(allowSetters = true)
    private Long id;
    private String accountNumber;
    private Long customerId;

    private boolean isForeign;
    private String currency;
//    @ReadOnlyProperty
    @JsonIgnoreProperties(allowSetters = true)
    private Long balance;
}
