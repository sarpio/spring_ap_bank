package com.example.demo.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Max;


@Table(name = "account")
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Max(9999)
    @Column(name = "account_number", unique = true)
    private Long accountNumber;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column(name = "balance")
    private Double balance;

}
