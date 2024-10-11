package com.example.springdatajpa_creditcard_demo.repositories;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.springdatajpa_creditcard_demo.model.CreditCard;
import com.example.springdatajpa_creditcard_demo.services.EncryptionService;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CreditCardRepoTest {

    final String CREDIT_CARD = "12345678900000";

    @Autowired
    CreditCardRepo creditCardRepo;
    
    @Autowired
    EncryptionService encryptionService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void test_saveAndStore_creditcard() {
        var creditcard = new CreditCard();

        creditcard.setCreditCardNumber(CREDIT_CARD);
        creditcard.setCvv("123");
        creditcard.setExpirationDate("12/2028");

        var savedCC = creditCardRepo.saveAndFlush(creditcard);

        System.out.println("Getting CreditCard from database: " + savedCC.getCreditCardNumber());
        System.out.println("CC at rest");
        System.out.println("CC encrypted: " + encryptionService.encrypt(CREDIT_CARD));

        //verifying data directly from database
        Map<String, Object> dbRow = jdbcTemplate.queryForMap("SELECT * FROM credit_card " +
        "WHERE id = " + savedCC.getId());

        String dbCardValue =(String) dbRow.get("credit_card_number");

        assertThat(savedCC.getCreditCardNumber()).isNotEqualTo(dbCardValue);
        assertThat(dbCardValue).isEqualTo(encryptionService.encrypt(CREDIT_CARD));

        var fetchedCC = creditCardRepo.findById(savedCC.getId()).get();

        assertThat(savedCC.getCreditCardNumber()).isEqualTo(fetchedCC.getCreditCardNumber());
    }
}
 