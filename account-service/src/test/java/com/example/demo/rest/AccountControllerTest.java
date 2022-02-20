package com.example.demo.rest;

import com.example.demo.entity.Account;
import com.example.demo.entity.Currency;
import com.example.demo.repo.AccountCache;
import com.example.demo.repo.AccountRepository;
import com.example.demo.rest.dto.AccountDTO;
import com.example.demo.rest.dto.OperationDTO;
import com.example.demo.services.AccountServices;
import com.example.demo.services.OperationFeignClient;
import com.example.demo.until.EntityDtoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static javax.security.auth.callback.ConfirmationCallback.OK;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {
    private MockMvc mockMvc;

    @Mock
    private AccountServices accountServices;

    @InjectMocks
    private AccountController accountController;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();
    Account account1 = new Account(1L, 1111L, 1L,  Currency.PLN, 50000.00);
    Account account2 = new Account(2L, 2222L, 2L, Currency.PLN, 10000.00);
    Account account3 = new Account(3L, 3333L, 3L, Currency.PLN, 8000.00);
    List<OperationDTO> operationDTOList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllAccounts() throws Exception {
        List<Account> accounts = new ArrayList<>(Arrays.asList(account1, account2, account3));
        List<AccountDTO> accountDTOS = accounts.stream()
                .map(EntityDtoMapper::map)
                .collect(Collectors.toList());
      Mockito.when(accountServices.findAllAccounts()).thenReturn(accountDTOS);
        ResponseEntity<List<AccountDTO>> allAccounts = accountController.getAllAccounts();
        Assertions.assertEquals(200, allAccounts.getStatusCodeValue());
        Assertions.assertTrue(allAccounts.hasBody());
        Assertions.assertNotNull(allAccounts);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/account")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
//             .andExpect(jsonPath("$[2].accountNumber", is(2222L)));


    }

    @Test
    void getAccountById() throws Exception {
        Mockito.when(accountServices.findAccountById(1L)).thenReturn(EntityDtoMapper.map(account1));

        AccountDTO accountById = accountController.getAccountById(1L).getBody();
//        Assertions.assertNotNull(accountById);
//        Assertions.assertEquals(accountById.getId(), 1L);
//        Assertions.assertEquals(1111L, accountById.getAccountNumber());
//        Assertions.assertEquals(50000.00, accountById.getBalance());
//        Assertions.assertEquals(Currency.PLN, accountById.getCurrency());
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/api/account/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());

    }

    @Test
    void getAccountByCustomerId() {
    }

    @Test
    void createAccount() {
    }

    @Test
    void deleteAccountById() {
    }

    @Test
    void updateAccountBalance() {
    }
}