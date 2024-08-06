package com.marie.paymentsystem.model.repository;

import com.marie.paymentsystem.model.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository <Transaction, Long> {

    List<Transaction> findByAppUserId(Long userId);
}
