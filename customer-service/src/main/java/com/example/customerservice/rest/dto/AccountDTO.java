package com.example.customerservice.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class AccountDTO {
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Long id;
    @NotNull
    @Min(value = 1000, message = "the value have to start on 1 and have to has 4 numbers")
    @Max(value = 9999, message = "max value is 9999")
    private Long accountNumber;
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Long customerId;
    @NotNull(message = "Currency type must be selected from valid symbols")
    @Enumerated(EnumType.STRING)
    private String currency;
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Long balance;
}
