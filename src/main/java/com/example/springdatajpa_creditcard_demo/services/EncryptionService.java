package com.example.springdatajpa_creditcard_demo.services;

public interface EncryptionService {

    String encrypt(String freeText);

    String decrypt(String encryptedText);
}