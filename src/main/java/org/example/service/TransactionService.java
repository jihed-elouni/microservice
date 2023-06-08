package org.example.service;

import org.example.model.Transaction;
import org.example.model.TransactionDto;
import org.example.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionService implements ITransactionService{
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionDto> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<TransactionDto> getTransactionById(UUID id) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        return optionalTransaction.map(this::convertToDto);
    }

    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Transaction transaction = convertToEntity(transactionDto);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return convertToDto(savedTransaction);
    }

    public Optional<TransactionDto> updateTransaction(UUID id, TransactionDto transactionDto) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        optionalTransaction.ifPresent(transaction -> {
            transaction.setTransactionType(transactionDto.getTransactionType());
            transaction.setSourceAccount(transactionDto.getSourceAccount());
            transaction.setDestinationAccount(transactionDto.getDestinationAccount());
            transaction.setAmount(transactionDto.getAmount());
            transaction.setCurrency(transactionDto.getCurrency());
            transactionRepository.save(transaction);
        });
        return optionalTransaction.map(this::convertToDto);
    }

    public void deleteTransaction(UUID id) {
        transactionRepository.deleteById(id);
    }

    private TransactionDto convertToDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setTransactionType(transaction.getTransactionType());
        transactionDto.setSourceAccount(transaction.getSourceAccount());
        transactionDto.setDestinationAccount(transaction.getDestinationAccount());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setCurrency(transaction.getCurrency());
        return transactionDto;
    }

    private Transaction convertToEntity(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setId(transactionDto.getId());
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setSourceAccount(transactionDto.getSourceAccount());
        transaction.setDestinationAccount(transactionDto.getDestinationAccount());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setCurrency(transactionDto.getCurrency());
        return transaction;
    }
}
