package com.example.drinkstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

    private List<String> allowedNames;

    @PostConstruct
    public void init(){
        this.allowedNames = new ArrayList<>();
        this.allowedNames.add("User");
    }

    @GetMapping
    public String getLoginPage(){
        return "login";
    }

//    @PostMapping
//    public String loginUser(HttpServletRequest req){
//
//    }
}
