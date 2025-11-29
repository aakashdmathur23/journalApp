package com.adm.journalApp.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.adm.journalApp.entity.User;
import com.adm.journalApp.service.UserArgumentsProvider;

@SpringBootTest
public class UserRepositoryImplTests {


    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    
    @Test
    public void testSaveNewUser(){
        Assertions.assertNotNull(userRepositoryImpl.getUserForSA());
    }
}
