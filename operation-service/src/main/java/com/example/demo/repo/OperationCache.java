package com.example.demo.repo;

import com.example.demo.config.CacheConfig;
import com.example.demo.rest.dto.OperationDTO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OperationCache {
    @Cacheable(key = "#id", cacheManager = CacheConfig.Operation_DTO_Cache_Manager,
            cacheNames = CacheConfig.Operation_DTO_Cache_Name)
    public Optional<OperationDTO> getOperation(Long id) {
        return Optional.empty();
    }

    @CachePut(key = "#operationDTO.id", cacheManager = CacheConfig.Operation_DTO_Cache_Manager,
            cacheNames = CacheConfig.Operation_DTO_Cache_Name)
    public void saveOperationInCache(OperationDTO operationDTO) {
    }

    @CacheEvict(key = "#id", cacheManager = CacheConfig.Operation_DTO_Cache_Manager,
            cacheNames = CacheConfig.Operation_DTO_Cache_Name)
    public void deleteOperationFromCache(Long id) {
    }
}
