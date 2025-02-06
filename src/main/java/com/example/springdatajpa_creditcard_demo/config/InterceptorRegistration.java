package com.example.springdatajpa_creditcard_demo.config;

import java.util.Map;

import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
//import org.springframework.context.annotation.Configuration;

//import com.example.springdatajpa_creditcard_demo.interceptors.EncryptionInterceptor;

//@Configuration
public class InterceptorRegistration implements HibernatePropertiesCustomizer{

   // private final EncryptionInterceptor interceptor;

    //public InterceptorRegistration(EncryptionInterceptor interceptor) {
    //    this.interceptor = interceptor;
    //}

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        //hibernateProperties.put("hibernate.session_factory.interceptor", interceptor);
    }
    
}
