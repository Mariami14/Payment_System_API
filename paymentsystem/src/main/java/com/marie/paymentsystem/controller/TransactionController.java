package com.marie.paymentsystem.controller;


import com.marie.paymentsystem.DTO.TransactionDTO;
import com.marie.paymentsystem.model.model.AppUser;
import com.marie.paymentsystem.model.model.Product;
import com.marie.paymentsystem.model.model.Transaction;
import com.marie.paymentsystem.service.ProductService;
import com.marie.paymentsystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        try {
            // Validate product
            Product product = productService.getProductById(transactionDTO.getProductId());
            if (product == null) {
                return ResponseEntity.badRequest().body("Invalid product selected.");
            }

            // Validate card details if payment method is card
            if ("CARD".equals(transactionDTO.getPaymentMethod())) {
                if (transactionDTO.getCardNumber() == null || transactionDTO.getCardNumber().isEmpty() ||
                        transactionDTO.getExpirationDate() == null || transactionDTO.getCVV() == null || transactionDTO.getCVV().isEmpty()) {
                    return ResponseEntity.badRequest().body("Card details are required for card payments.");
                }
            }

            // Process transaction
            Transaction transaction = new Transaction();
            transaction.setAppUser(getAuthenticatedUser());
            transaction.setProduct(product);
            transaction.setAmount(transactionDTO.getAmount());
            transaction.setPaymentMethod(transactionDTO.getPaymentMethod());
            transaction.setCardNumber(transactionDTO.getCardNumber());
            transaction.setTransactionDate(new Date());
            transaction.setStatus("SUCCESS"); // or "FAILED" based on the result

            transactionService.saveTransaction(transaction);

            return ResponseEntity.ok(transaction);
        } catch (Exception e) {
            // Log the exception
            return ResponseEntity.badRequest().body("Transaction failed: " + e.getMessage());
        }
    }
    //to be changed
    private AppUser getAuthenticatedUser() {
        return null;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getTransactionsByUser(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(transactionService.getTransactionsByUser(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

