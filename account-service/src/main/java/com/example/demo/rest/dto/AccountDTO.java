package com.example.demo.rest.dto;

import com.example.demo.entity.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "account_number", unique = true)
    private Long accountNumber;

    @NotNull
    @Min(value = 1, message = "CustomerId must be above zero")
    private Long customerId;

    private Integer isForeign;
    @NotNull(message = "Currency type must be selected from valid symbols")
    private Currency currency;

    private Double balance;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<OperationDTO> operations;
}
