package com.example.springdatajpa_creditcard_demo.listeners;

import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.springframework.stereotype.Component;

import com.example.springdatajpa_creditcard_demo.services.EncryptionService;

@Component
public class PostLoadListener extends AbstractEncryptionListener implements PostLoadEventListener{

    public PostLoadListener(EncryptionService encryptionService) {
        super(encryptionService);
    }

    @Override
    public void onPostLoad(PostLoadEvent event) {
        System.out.println("I am post-load.....");
        this.decrypt(event.getEntity());
    }
    
}
