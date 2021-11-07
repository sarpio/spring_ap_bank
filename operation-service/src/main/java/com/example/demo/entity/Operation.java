package com.example.demo.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "operation")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Operation {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(name = "account_number")
    private long accountNumber;
    @Column(name = "foreign_account")
    private long foreignAccount;
    @Column(name = "transaction_data")
    private LocalDateTime transactionData;
    @Column(name = "value")
    private double value;
    @Column(name = "type")
    private Type type;

}

enum Type {
    EXPENSE, INCOME
}
