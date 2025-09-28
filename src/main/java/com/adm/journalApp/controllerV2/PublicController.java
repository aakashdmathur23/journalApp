package com.adm.journalApp.controllerV2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adm.journalApp.controllerV2.service.UserService;
import com.adm.journalApp.entity.User;

@RestController
@RequestMapping("/public")
public class PublicController {

    private final UserService userService;

    public PublicController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.saveUser(user);
    }
}
