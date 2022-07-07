package com.nvq.RegistrationAndLogin.controller;

import com.nvq.RegistrationAndLogin.Repository.UserRepository;
import com.nvq.RegistrationAndLogin.Service.UserServices;
import com.nvq.RegistrationAndLogin.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class AppController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServices services;

    @GetMapping
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signUp_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        services.register(user, getSiteURL(request));

        userRepository.save(user);

        return "register_sucess";
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(),"");
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepository.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (services.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }
}
