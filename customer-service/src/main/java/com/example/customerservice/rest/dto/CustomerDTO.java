package com.example.customerservice.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @Unique
    @NotBlank
    @NotNull
    private String name;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<AccountDTO> accounts;
}
