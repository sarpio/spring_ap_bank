package com.example.demo.repo;

import com.example.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByCustomerId(Long id);

    @Query(value = "select * from account a where a.account_number =?1", nativeQuery = true)
    Boolean findByAccountNumber(String number);
}
