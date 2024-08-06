package com.marie.paymentsystem.service;

import com.marie.paymentsystem.model.model.Product;
import com.marie.paymentsystem.model.model.Transaction;
import com.marie.paymentsystem.model.repository.ProductRepository;
import com.marie.paymentsystem.model.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProductRepository productRepository;

    public Transaction createTransaction(Transaction transaction) {
        Product product = productRepository.findById(transaction.getProduct().getId()).orElseThrow(() -> new RuntimeException("Invalid product"));

        if (transaction.getAmount() < product.getMinDeposit() || transaction.getAmount() > product.getMaxDeposit()) {
            throw new RuntimeException("Invalid deposit amount");
        }

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByUser(Long userId) {
        return transactionRepository.findByAppUserId(userId);
    }
}
