package org.example.controller;

import org.example.model.TransactionDto;
import org.example.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<TransactionDto> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public TransactionDto getTransactionById(@PathVariable UUID id) {
        return transactionService.getTransactionById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
    }

    @PostMapping
    public TransactionDto createTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionService.createTransaction(transactionDto);
    }

    @PutMapping("/{id}")
    public TransactionDto updateTransaction(@PathVariable UUID id, @RequestBody TransactionDto transactionDto) {
        return transactionService.updateTransaction(id, transactionDto)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable UUID id) {
        transactionService.deleteTransaction(id);
    }
}
