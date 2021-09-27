package com.example.demo.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Table(name = "account")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Account {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(name = "account_number")//generate auto ?
    private long accountNumber;
    @Column(name = "customer_Id")
    private UUID customerId;
    @Column(name = "is_Foreign")
    private boolean isForeign;
    @Column(name = "account_number")
    private Type type;
}

enum Type {

}
