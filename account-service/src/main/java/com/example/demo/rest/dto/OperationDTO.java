package com.example.demo.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OperationDTO {

    private Long id;
    private Long accountId;
    private Long foreignAccount;
    private String transactionDate;
    private double value;
    private Type type;
}
