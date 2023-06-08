package org.example.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private String sourceAccount;
    private String destinationAccount;
    private double amount;
    private String currency;
}
