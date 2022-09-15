package com.mvp.question.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long adderess_id;
    @Column(length = 20)
    private String coin_type;
    @Column
    private Float amount;
    @Column
    private Integer receive_date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Wallet() {
    }

    public Wallet(Long adderess_id, String coin_type, Float amount, User user) {
        this.adderess_id = adderess_id;
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

    public Long getAdderess_id() {
        return adderess_id;
    }

    public void setAdderess_id(Long adderess_id) {
        this.adderess_id = adderess_id;
    }

    public String getCoin_type() {
        return coin_type;
    }

    public void setCoin_type(String coin_type) {
        this.coin_type = coin_type;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Integer getReceive_date() {
        return receive_date;
    }

    public void setReceive_date(Integer receive_date) {
        this.receive_date = receive_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
