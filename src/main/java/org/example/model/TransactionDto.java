package org.example.model;

import lombok.Data;

import java.util.UUID;

@Data
public
class TransactionDto {
    private UUID id;
    private TransactionType transactionType;
    private String sourceAccount;
    private String destinationAccount;
    private double amount;
    private String currency;
}
