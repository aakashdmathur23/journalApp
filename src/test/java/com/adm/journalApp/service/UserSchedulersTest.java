package com.adm.journalApp.service;

import org.junit.jupiter.api.Test;

import com.adm.journalApp.config.SpringBootTest;
import com.adm.journalApp.scheduler.UserScheduler;

@SpringBootTest
public class UserSchedulersTest {

    private UserScheduler userscheduler;


    @Test
    public void testFetchUsersAndSendSAMail(){
        userscheduler.fetchUsersAndSendSaMail();
    }
}
