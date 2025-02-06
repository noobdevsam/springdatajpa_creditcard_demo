package com.example.springdatajpa_creditcard_demo.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

// Getting hold on the spring context itself for customizing the context
@Component
public class SpringContextHelper implements ApplicationContextAware{

    private static ApplicationContext applicationContext;

    @SuppressWarnings("null")
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHelper.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    
}
