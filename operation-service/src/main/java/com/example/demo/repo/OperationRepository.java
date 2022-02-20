package com.example.demo.repo;

import com.example.demo.entity.Operation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "id")
    List<Operation> findByAccountId(Long id);

}
