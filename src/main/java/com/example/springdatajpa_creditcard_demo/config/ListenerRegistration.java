package com.example.springdatajpa_creditcard_demo.config;

import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import com.example.springdatajpa_creditcard_demo.listeners.PostLoadListener;
import com.example.springdatajpa_creditcard_demo.listeners.PreInsertListener;
import com.example.springdatajpa_creditcard_demo.listeners.PreUpdateListener;

@Component
public class ListenerRegistration implements BeanPostProcessor{

    private final PreInsertListener preInsertListener;
    private final PreUpdateListener preUpdateListener;
    private final PostLoadListener postLoadListener;

    public ListenerRegistration(PreInsertListener preInsertListener, PreUpdateListener preUpdateListener,
            PostLoadListener postLoadListener) {
        this.preInsertListener = preInsertListener;
        this.preUpdateListener = preUpdateListener;
        this.postLoadListener = postLoadListener;
    }

    @Override
    @Nullable
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    @Nullable
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if(bean instanceof LocalContainerEntityManagerFactoryBean) {
            var lemf = (LocalContainerEntityManagerFactoryBean) bean;
            var sessionFactory = (SessionFactoryImpl) lemf.getNativeEntityManagerFactory();
            var registry = sessionFactory.getServiceRegistry()
                                .getService(EventListenerRegistry.class);

            registry.appendListeners(EventType.PRE_INSERT, preInsertListener);
            registry.appendListeners(EventType.PRE_UPDATE, preUpdateListener);
            registry.appendListeners(EventType.POST_LOAD, postLoadListener);
        }

        return bean;
    }
    
}
