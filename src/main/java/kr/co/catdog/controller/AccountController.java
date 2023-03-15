package kr.co.catdog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
    @GetMapping("/login")
    String login() {
        return "sign-in";
    }
    @GetMapping("/signup")
    String signup(){
        return "sign-up";
    }
}
