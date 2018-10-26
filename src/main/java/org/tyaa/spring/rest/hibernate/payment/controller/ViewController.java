/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.spring.rest.hibernate.payment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Yurii
 */
@Controller
public class ViewController {
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    
    @GetMapping("/shopping")
    public String shopping() {
        return "shopping";
    }
    
    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }
    
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
    
    @GetMapping("/admin/dashboard")
    public String dashboard() {
        return "dashboard";
    }
    
    @GetMapping("/admin/adminunit")
    public String adminunit() {
        return "adminunit";
    }
}
