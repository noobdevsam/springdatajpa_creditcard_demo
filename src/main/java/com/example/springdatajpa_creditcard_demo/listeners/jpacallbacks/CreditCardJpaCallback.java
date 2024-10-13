package com.example.springdatajpa_creditcard_demo.listeners.jpacallbacks;

import com.example.springdatajpa_creditcard_demo.model.CreditCard;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class CreditCardJpaCallback {

    @PrePersist
    @PreUpdate
    public void beforeInsertOrUpdate(CreditCard creditCard) {
        System.out.println("before update was called....");
    }

    @PostPersist
    @PostLoad
    @PostUpdate
    public void postLoad(CreditCard creditCard) {
        System.out.println("Post load was called....");
    }
}