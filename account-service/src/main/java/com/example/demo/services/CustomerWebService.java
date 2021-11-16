package com.example.demo.services;

import com.example.demo.rest.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerWebService {
    private final WebClient webClient;

    @Autowired
    public CustomerWebService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8200/api/customer/").build();
    }

    public Flux<CustomerDTO> getAccount(Long id) {
        return this.webClient
                .get()
                .uri("{id}", id)
                .retrieve()
                .bodyToFlux(CustomerDTO.class);
    }

    public Flux<List<CustomerDTO>> getAllCustomerAccounts(Long id) {
        return this.webClient
                .get()
                .uri("{id}", id)
                .retrieve()
                .bodyToFlux(CustomerDTO.class)
                .buffer();
    }
}
