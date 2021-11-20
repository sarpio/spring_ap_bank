package com.example.demo.rest.dto;

import com.example.demo.entity.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
public class AccountDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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
    private List<OperationDTO> operations;

}
