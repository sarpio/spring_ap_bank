package com.example.customerservice.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import org.checkerframework.common.aliasing.qual.Unique;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
public class CustomerDTOPost {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Unique
    @NotBlank(message = "Customer name cannot be blank")
    @NotNull(message = "Customer name cannot be null")
    private String name;
    private String accountNumber;

    @NotBlank(message = "Currency name cannot be blank")
    @NotNull(message = "Currency name cannot be null")
    private String currency;
}
