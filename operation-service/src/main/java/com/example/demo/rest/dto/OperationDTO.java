package com.example.demo.rest.dto;

import com.example.demo.entity.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Data
@Builder
public class OperationDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @Min(1000)@Max(9999)
    private Long foreignAccount;

    @PastOrPresent
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime transactionDate;
    @Min(1/10)
    private double value;
    private Type type;
}
