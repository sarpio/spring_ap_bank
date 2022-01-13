package com.example.demo.repo;

import com.example.demo.config.CacheConfig;
import com.example.demo.rest.dto.AccountDTO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountCache {
    @Cacheable(key = "#id", cacheManager = CacheConfig.Account_DTO_Cache_Manager,
            cacheNames = CacheConfig.Account_DTO_Cache_Name)
    public Optional<AccountDTO> getAccount(Long id) {
        return Optional.empty();
    }

    @Cacheable(key = "#customerId", cacheManager = CacheConfig.Account_DTO_Cache_Manager,
            cacheNames = CacheConfig.Account_DTO_Cache_Name)
    public List<AccountDTO> getAccountByCustomerId(Long customerId) {
        return new ArrayList<>();
    }

    @CachePut(key = "#accountDTO.id", cacheManager = CacheConfig.Account_DTO_Cache_Manager,
            cacheNames = CacheConfig.Account_DTO_Cache_Name)
    public void saveAccountInCache(AccountDTO accountDTO) {
    }

    @CacheEvict(key = "#id", cacheManager = CacheConfig.Account_DTO_Cache_Manager,
            cacheNames = CacheConfig.Account_DTO_Cache_Name)
    public void deleteAccountFromCache(Long id) {
    }
}
