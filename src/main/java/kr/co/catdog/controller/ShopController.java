package kr.co.catdog.controller;

import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/user/shop")
public class ShopController {
    @GetMapping("/cart")
    String cart() {
        return "user/shop/cart";
    }

    @GetMapping("/list")
    String list() {
        return "user/shop/list";
    }

    @GetMapping("/detail")
    String detail() {
        return "user/shop/detail";
    }

    @GetMapping("/register")
    String register() {
        return "user/shop/register";
    }

    @GetMapping("/edit")
    String edit() {
        return "user/shop/edit";
    }

    @GetMapping("/delete")
    String delete(){
        return "redirect:/user/shop/list";
    }


}
