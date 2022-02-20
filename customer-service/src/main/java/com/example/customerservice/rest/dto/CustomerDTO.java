package com.example.customerservice.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
    @NotBlank(message = "Customer name cannot be blank")
    @NotNull(message = "Customer name cannot be null")
    private String name;
    @NotNull(message = "Customer cannot exist without account")
    private List<AccountDTO> accounts;
}
