package com.example.springdatajpa_creditcard_demo.repositories;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.springdatajpa_creditcard_demo.model.CreditCard;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CreditCardRepoTest {

    final String CREDIT_CARD = "12345678900000";

    @Autowired
    CreditCardRepo creditCardRepo;

    @Test
    void test_saveAndStore_creditcard() {
        var creditcard = new CreditCard();

        creditcard.setCreditCardNumber(CREDIT_CARD);
        creditcard.setCvv("123");
        creditcard.setExpirationDate("12/2028");

        var savedCC = creditCardRepo.saveAndFlush(creditcard);

        System.out.println("Getting CreditCard from database: ");

        var fetchedCC = creditCardRepo.findById(savedCC.getId()).get();

        assertThat(savedCC.getCreditCardNumber()).isEqualTo(fetchedCC.getCreditCardNumber());
    }
}
