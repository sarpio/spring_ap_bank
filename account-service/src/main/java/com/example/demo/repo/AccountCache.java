package com.example.demo.repo;

import com.example.demo.config.CacheConfig;
import com.example.demo.rest.dto.AccountDTO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AccountCache {
    @Cacheable(key = "#id", cacheManager = CacheConfig.Account_DTO_Cache_Manager,
            cacheNames = CacheConfig.Account_DTO_Cache_Name)
    public Optional<AccountDTO> getAccount(Long id) {
        return Optional.empty();
    }

    @CachePut(key = "#accountDTO.id", cacheManager = CacheConfig.Account_DTO_Cache_Manager,
            cacheNames = CacheConfig.Account_DTO_Cache_Name)
    public AccountDTO saveAccountInCache(AccountDTO accountDTO) {return  accountDTO;
    }

    @CacheEvict(key = "#id", cacheManager = CacheConfig.Account_DTO_Cache_Manager,
            cacheNames = CacheConfig.Account_DTO_Cache_Name)
    public void deleteAccountFromCache(Long id) {
    }
}
