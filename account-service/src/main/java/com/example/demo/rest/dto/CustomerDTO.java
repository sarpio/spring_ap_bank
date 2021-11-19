package com.example.demo.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class CustomerDTO {

    private Long id;
    private String name;
    private List<AccountDTO> accounts;

}
