package com.example.demo.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Builder
public class CustomerDTO {

    private Long id;
    private String name;
    private List<AccountDTO> accounts;

}
