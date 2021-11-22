package com.example.customerservice.service;

import com.example.customerservice.rest.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class AccountWebService {

    private final WebClient webClient;

    @Autowired
    public AccountWebService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8100/api/account/").build();
    }

    public Flux<AccountDTO> getAccount(Long id) {
        return this.webClient
                .get()
                .uri("customer/{id}", id)
                .retrieve()
                .bodyToFlux(AccountDTO.class);
    }

    public Flux<List<AccountDTO>> getAllCustomerAccounts(Long id) {
        return this.webClient
                .get()
                .uri("/customer/{id}", id)
                .retrieve()
                .bodyToFlux(AccountDTO.class)
                .buffer();
    }
}
