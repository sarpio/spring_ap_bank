package com.example.customerservice.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import java.util.Random;

@Data
@Builder
public class AccountDTO {

    private Long id;
    private String accountNumber;
//    private Long customerId;
    @JsonIgnore
    private CustomerDTO customer;
    private boolean isForeign;
    private String currency;
    private Long balance;
}
