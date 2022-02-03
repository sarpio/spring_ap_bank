package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "foreign_account")
    private Long foreignAccount;

    @Column(name = "transaction_date")
    private String transactionDate;

    @Column(name = "value")
    private double value;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;
}
