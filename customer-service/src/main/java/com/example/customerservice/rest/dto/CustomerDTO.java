package com.example.customerservice.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Long id;
    private String name;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<AccountDTO> accounts;
}
