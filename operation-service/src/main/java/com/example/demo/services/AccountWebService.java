package com.example.demo.services;

import com.example.demo.rest.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AccountWebService {

    private final WebClient webClient;

    @Autowired
    public AccountWebService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8100/api/account/")
                .build();
    }

    public Mono<AccountDTO> getAccount(Long id) {
        return this.webClient
                .get()
                .uri("{id}", id)
                .retrieve()
                .bodyToMono(AccountDTO.class);
    }

    public Mono<AccountDTO> putAccount(AccountDTO account) {
        return this.webClient.put()
                .uri("" + account.getId())
                .bodyValue(account)
                .retrieve()
                .bodyToMono(AccountDTO.class);
    }
}
