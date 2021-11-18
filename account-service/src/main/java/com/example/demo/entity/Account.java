package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.ws.rs.DefaultValue;

@Table(name = "account")
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "customer_id")
    private Long customerId;

    @Value("false")
    @Column(name = "is_Foreign")
    private Integer isForeign;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private Double balance;

}
