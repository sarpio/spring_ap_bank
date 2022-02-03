package com.example.demo.rest.dto;

import com.example.demo.entity.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull(message = "Account Id cannot be null")
    @Positive(message = "Account Id must have positive value")
    private Long accountId;

    @Min(value = 1000, message = "Foreign Account number must be 4 digits")
    @Max(value = 9999, message = "Foreign Account number must be 4 digits")
    private Long foreignAccount;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String transactionDate;

    @Min(value = 1, message = "Minimum value is 1")
    private double value;

    @Enumerated(EnumType.STRING)
    private Type type;
}
