package com.example.demo.repo;

import com.example.demo.entity.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "id")
    List<Account> findByCustomerId(Long id);

    Optional<Account> findByAccountNumber(Long number);

    @Override
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "id")
    List<Account> findAll();
}
