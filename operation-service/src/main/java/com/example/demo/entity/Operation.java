package com.example.demo.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name = "foreign_account")
    private Long foreignAccount;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "value")
    private double value;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;
}
