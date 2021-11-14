package com.example.demo.repo;

import com.example.demo.entity.Account;
import com.example.demo.rest.dto.AccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

//    @Query(value = "SELECT * FROM ACCOUNT a WHERE a.customer_id=:id", nativeQuery = true)
    List<Account> findByCustomerId(Long id);
}
