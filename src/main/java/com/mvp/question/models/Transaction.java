package com.mvp.question.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 32)
    private TransactionType type; // withdraw -> bardasht or deposit -> variz
    @Column(length = 256)
    private String to;
    @Enumerated(EnumType.STRING)
    @Column(length = 32)
    private CoinType coin_type;
    @Column
    private Float amount;

    @ManyToOne
    @JoinColumn(name = "transaction_user_id")
    private User user;

    public Transaction() {
    }

    public Transaction(TransactionType type, String to, CoinType coin_type, Float amount, User user) {
        this.type = type;
        this.to = to;
        this.coin_type = coin_type;
        this.amount = amount;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public CoinType getCoin_type() {
        return coin_type;
    }

    public void setCoin_type(CoinType coin_type) {
        this.coin_type = coin_type;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

}
