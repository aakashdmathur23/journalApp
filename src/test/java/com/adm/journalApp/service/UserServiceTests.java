package com.adm.journalApp.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.adm.journalApp.controllerV2.service.UserService;
import com.adm.journalApp.entity.User;
import com.adm.journalApp.repository.UserRepository;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setup(){
        
    }
    
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){
        assertTrue(userService.saveNewUser(user));
    }

    @Disabled 
    @ParameterizedTest
    @CsvSource({
        "1, 2, 3",
        "4, 5, 9",
        "10, 20, 30"
    })
    public void test(int a, int b, int expected) {
        assertEquals(expected, a+b);
    }
}