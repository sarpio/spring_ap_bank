package com.example.customerservice.repo;

import com.example.customerservice.config.CacheConfig;
import com.example.customerservice.rest.dto.CustomerDTO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomerCache {
    @Cacheable(key = "#id", cacheManager = CacheConfig.Customer_DTO_Cache_Manager,
            cacheNames = CacheConfig.Customer_DTO_Cache_Name)
    public Optional<CustomerDTO> getCustomer(Long id) {
        return Optional.empty();
    }

    @CachePut(key = "#customerDto.id", cacheManager = CacheConfig.Customer_DTO_Cache_Manager,
            cacheNames = CacheConfig.Customer_DTO_Cache_Name)
    public void saveCustomerInCache(CustomerDTO customerDto) {
    }

    @CacheEvict(key = "#id", cacheManager = CacheConfig.Customer_DTO_Cache_Manager,
            cacheNames = CacheConfig.Customer_DTO_Cache_Name)
    public void deleteCustomerFromCache(Long id) {
    }
}
