package com.adm.journalApp.controllerV2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adm.journalApp.controllerV2.service.UserService;


@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }

    @PostMapping
    public void createUser(RequestBody User user) {
        UserService.saveEntry(user);
    }

}
