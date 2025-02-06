package com.example.springdatajpa_creditcard_demo.listeners.jpacallbacks;

import com.example.springdatajpa_creditcard_demo.config.SpringContextHelper;
import com.example.springdatajpa_creditcard_demo.model.CreditCard;
import com.example.springdatajpa_creditcard_demo.services.EncryptionService;

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
        creditCard.setCreditCardNumber(
            getEncryptionService().encrypt(
                creditCard.getCreditCardNumber()
            )
        );
    }

    @PostPersist
    @PostLoad
    @PostUpdate
    public void postLoad(CreditCard creditCard) {
        System.out.println("Post load was called....");
        creditCard.setCreditCardNumber(
            getEncryptionService().decrypt(
                creditCard.getCreditCardNumber()
            )
        );
    }

    // accessing spring context for accessing a specific part of the context
    private EncryptionService getEncryptionService() {
        return SpringContextHelper.getApplicationContext().getBean(EncryptionService.class);
    }
}