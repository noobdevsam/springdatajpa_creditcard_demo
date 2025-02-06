package com.example.springdatajpa_creditcard_demo.model.converters;

import com.example.springdatajpa_creditcard_demo.config.SpringContextHelper;
import com.example.springdatajpa_creditcard_demo.services.EncryptionService;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// here, AttributeConverter takes "String", returns "String"
@Converter
public class CreditCardConverter implements AttributeConverter<String, String>{

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return getEncryptionService().encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return getEncryptionService().decrypt(dbData);
    }

    //access the application context
    private EncryptionService getEncryptionService() {
        return SpringContextHelper.getApplicationContext().getBean(EncryptionService.class);
    }
    
}
