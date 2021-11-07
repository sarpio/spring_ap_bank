package com.example.demo.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.util.UUID;

@Table(name = "account")
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "account_number") //generate auto ?
    private String accountNumber;

    @Column(name = "customer_Id")
    private UUID customerId;

    @Column(name = "is_Foreign")
    private Boolean isForeign;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private Type type;

    private Long balance;
}
