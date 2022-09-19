package com.mvp.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mvp.question.models.Wallet;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Long> {

}
