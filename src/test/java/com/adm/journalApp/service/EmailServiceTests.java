package com.adm.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.adm.journalApp.config.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {
    
    @Autowired
    private EmailService emailService;


    @Test
    void testSendMail(){
        emailService.sendEmail("aakashdmathur@gmail.com", "Testing Java Mail Sender", "Hi, Aap kaise hai ?");
    }

}
