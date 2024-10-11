package com.example.springdatajpa_creditcard_demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springdatajpa_creditcard_demo.model.CreditCard;

public interface CreditCardRepo extends JpaRepository<CreditCard, Long>{
    
}
