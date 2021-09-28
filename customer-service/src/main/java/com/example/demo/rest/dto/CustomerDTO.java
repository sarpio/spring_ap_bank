package com.example.demo.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    @Schema(description = "client name", example = "Adam Nowak", required = true)
    @NotNull(message = "not be null")
    @NotBlank(message = "not be blank")
    private String name;
    @Schema(description = "customers bank", required = true)
    private BankDTO bankDTO;
}
