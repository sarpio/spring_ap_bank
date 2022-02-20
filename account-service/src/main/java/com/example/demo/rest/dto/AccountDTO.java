package com.example.demo.rest.dto;

import com.example.demo.entity.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Max;
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

    @NotNull
    @Min(value = 1000, message = "the value have to start on 1 and have to has 4 numbers")
    @Max(value = 9999, message = "max value is 9999")
    private Long accountNumber;

    @NotNull
    @Min(value = 1, message = "CustomerId must be above zero")
    private Long customerId;
    @NotNull(message = "Currency type must be selected from valid symbols")
    private Currency currency;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double balance;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<OperationDTO> operations;

    public void setAccountNumber() {
        this.accountNumber = (long) ((Math.random() * (9999 - 1000)) + 1000);
    }

}
