package com.adm.journalApp.controllerV2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adm.journalApp.controllerV2.service.UserService;
import com.adm.journalApp.entity.User;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired

    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
        User userinDb = userService.findByUserName(userName);
        if(userinDb != null){
            userinDb.setUserName(user.getUserName());
            userinDb.setPassword(user.getPassword());
            userService.saveEntry(userinDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
