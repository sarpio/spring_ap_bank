package com.example.demo.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class OperationDTO {

    private Long id;
    private Long accountId;
    @Min(1000)@Max(9999)
    private Long foreignAccount;
    @PastOrPresent
    private LocalDateTime transactionDate;
    @Min(1/10)
    private double value;
    private Type type;
}
