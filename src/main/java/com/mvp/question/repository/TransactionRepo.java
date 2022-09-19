package com.mvp.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mvp.question.models.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

}
