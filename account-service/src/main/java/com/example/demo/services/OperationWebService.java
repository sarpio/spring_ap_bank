package com.example.demo.services;

import com.example.demo.rest.dto.OperationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class OperationWebService {
    private final WebClient webClient;

    @Autowired
    public OperationWebService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8300/api/operation/").build();
    }

    public Flux<List<OperationDTO>> getAllCustomerAccountsByAccountId(Long id) {
        return this.webClient
                .get()
                .uri("{id}", id)
                .retrieve()
                .bodyToFlux(OperationDTO.class)
                .buffer();
    }
}
