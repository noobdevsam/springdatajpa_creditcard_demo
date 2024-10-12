package com.example.springdatajpa_creditcard_demo.listeners;

import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.springframework.stereotype.Component;

import com.example.springdatajpa_creditcard_demo.services.EncryptionService;

@Component
public class PreUpdateListener extends AbstractEncryptionListener implements PreUpdateEventListener{

    public PreUpdateListener(EncryptionService encryptionService) {
        super(encryptionService);
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        System.out.println("I am pre-update....");
        this.encrypt(event.getState(), event.getPersister().getPropertyNames(), event.getEntity());
        return false;
    }
    
}
