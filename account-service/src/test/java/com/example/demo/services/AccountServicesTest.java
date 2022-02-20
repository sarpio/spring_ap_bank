package com.example.demo.services;

import com.example.demo.entity.Account;
import com.example.demo.entity.Currency;
import com.example.demo.repo.AccountCache;
import com.example.demo.repo.AccountRepository;
import com.example.demo.rest.dto.AccountDTO;
import com.example.demo.rest.dto.OperationDTO;
import com.example.demo.until.EntityDtoMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


class AccountServicesTest {
    private MockMvc mockMvc;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private AccountCache accountCache;
    @Mock
    private OperationFeignClient operationFeignClient;
    @InjectMocks
    private AccountServices accountServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(accountServices).build();
    }


    Account account1 = new Account(1L, 1111L, 1L, Currency.PLN, 50000.00);
    Account account2 = new Account(2L, 2222L, 2L,  Currency.PLN, 10000.00);
    Account account3 = new Account(3L, 3333L, 3L,  Currency.PLN, 8000.00);
    List<OperationDTO> operationDTOList = new ArrayList<>();

    @Test
    void findAllAccounts() {
        List<Account> accounts = new ArrayList<>(Arrays.asList(account1, account2, account3));
        Mockito.when(accountRepository.findAll()).thenReturn(accounts);
        Mockito.when(operationFeignClient.getAllCustomerOperationByAccountId(1L)).thenReturn(operationDTOList);
        List<AccountDTO> accountDTOList = accountServices.findAllAccounts();
        Assertions.assertEquals(3, accountDTOList.size());
        Assertions.assertFalse(accountDTOList.isEmpty());
        Assertions.assertNotNull(accountDTOList);

    }

    @Test
    void findAccountById() {
        Mockito.when(accountCache.getAccount(1L)).thenReturn(Optional.empty());
        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(account1));
        Mockito.when(operationFeignClient.getAllCustomerOperationByAccountId(1L)).thenReturn(operationDTOList);
        AccountDTO accountDTO = accountServices.findAccountById(1L);
        Assertions.assertNotNull(accountDTO);
        Assertions.assertEquals(accountDTO.getId(), 1L);
        Assertions.assertEquals(1111L, accountDTO.getAccountNumber());
        Assertions.assertEquals(50000.00, accountDTO.getBalance());
        Assertions.assertEquals(Currency.PLN, accountDTO.getCurrency());
    }

    @Test
    void createAccount() {
        Mockito.when(accountRepository.save(Mockito.any())).thenReturn(account1);
        Mockito.when(accountCache.saveAccountInCache(Mockito.any())).thenReturn(EntityDtoMapper.map(account1));
        AccountDTO accountDTO = accountServices.createAccount(EntityDtoMapper.map(account1));
        Assertions.assertNotNull(accountDTO);
        Assertions.assertEquals(1L, accountDTO.getId());
        Assertions.assertEquals(1111L, accountDTO.getAccountNumber());
        Assertions.assertEquals(Currency.PLN, accountDTO.getCurrency());
        Assertions.assertEquals(0, accountDTO.getBalance());
        Assertions.assertEquals(1L, accountDTO.getCustomerId());

    }

    @Test
    void deleteAccountById() {
        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(account1));

    }

    @Test
    void findByCustomerId() {
        List<Account> accounts = new ArrayList<>(List.of(account1));
        Mockito.when(accountRepository.findByCustomerId(1L)).thenReturn(accounts);
        Mockito.when(operationFeignClient.getAllCustomerOperationByAccountId(1L)).thenReturn(operationDTOList);
        Mockito.when(accountCache.saveAccountInCache(Mockito.any())).thenReturn(EntityDtoMapper.map(account1));

        List<AccountDTO> accountDTOList = accountServices.findByCustomerId(1L);
        Assertions.assertNotNull(accountDTOList);
        Assertions.assertEquals(1, accountDTOList.size());
        Assertions.assertFalse(accountDTOList.isEmpty());


    }

    @Test
    void updateBalanceByAccountId() {
    }
}