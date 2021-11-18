package com.example.demo.rest.dto;

import com.example.demo.entity.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Random;

@Data
@Builder
public class AccountDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String accountNumber;
    @NotNull
    @Min(1)
    private Long customerId;
    @Min(0)
    @Max(1)
    @NotNull
    private Integer isForeign;
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Currency currency;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double balance;

}
