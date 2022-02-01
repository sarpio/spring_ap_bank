package com.example.demo.rest.dto;

import com.example.demo.entity.Type;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull@Positive
    private Long accountId;

    @Min(1000)@Max(9999)
    private Long foreignAccount;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String transactionDate;

    @Min(1/10)
    private double value;

    @Enumerated(EnumType.STRING)
    private Type type;
}
