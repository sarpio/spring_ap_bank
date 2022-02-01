package com.example.demo.rest.dto;

import com.example.demo.entity.Currency;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.*;
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
    @Min(1)
    private Long customerId;

    private Integer isForeign;
    @NotNull
    private Currency currency;

    private Double balance;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<OperationDTO> operations;
}
