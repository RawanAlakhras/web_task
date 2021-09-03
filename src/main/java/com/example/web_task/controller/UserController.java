package com.example.web_task.controller;

import com.example.web_task.Repository.UserRepository;
import com.example.web_task.model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String getIndex(){
        return "index.html";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login.html";
    }

    @GetMapping(value = "/signup")
    public String getSignUpPage() {
        return "signup";
    }

    @PostMapping(value = "/signup")
    public RedirectView attemptSignUp(@RequestParam String email ,
                                      @RequestParam String password ,
                                      @RequestParam String firstName,
                                      @RequestParam String lastName ){
        ApplicationUser newUser = new ApplicationUser(email , bCryptPasswordEncoder.encode(password), firstName , lastName);
        newUser = userRepository.save(newUser);

        return new RedirectView("/login");
    }

}
