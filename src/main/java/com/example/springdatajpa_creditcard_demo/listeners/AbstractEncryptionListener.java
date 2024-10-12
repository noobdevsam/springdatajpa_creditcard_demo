package com.example.springdatajpa_creditcard_demo.listeners;

import java.lang.reflect.Field;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import com.example.springdatajpa_creditcard_demo.interceptors.EncryptedString;
import com.example.springdatajpa_creditcard_demo.services.EncryptionService;

public abstract class AbstractEncryptionListener {
    
    private final EncryptionService encryptionService;

    public AbstractEncryptionListener(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    public void encrypt(Object[] state, String[] propertyNames, Object entity) {
        ReflectionUtils.doWithFields(entity.getClass(), field -> encrptField(field, state, propertyNames), this::isFieldEncrypted);
    }

    private boolean isFieldEncrypted(Field field) {
        return AnnotationUtils.findAnnotation(field, EncryptedString.class) != null;
    }

    private void encrptField(Field field, Object[] state, String[] propertyNames) {
        int index = getPropertyIndex(field.getName(), propertyNames);
        Object currentValue = state[index];
        state[index] = encryptionService.encrypt(currentValue.toString());
    }

    private int getPropertyIndex(String name, String[] properties) {
        
        for (int i = 0; i < properties.length; i++) {
            if (name.equals(properties[i])) {
                return i;
            }
        }

        // should not reach here.....
        throw new IllegalArgumentException("Property not found!!!!!");
    }

    public void decrypt(Object entity) {
        ReflectionUtils.doWithFields(entity.getClass(), field -> decryptField(field, entity), this::isFieldEncrypted);
    }

    @SuppressWarnings("null")
    private void decryptField(Field field, Object entity) {
        field.setAccessible(true);
        Object value = ReflectionUtils.getField(field, entity);
        ReflectionUtils.setField(field, entity, encryptionService.decrypt(value.toString()));
    }

}
