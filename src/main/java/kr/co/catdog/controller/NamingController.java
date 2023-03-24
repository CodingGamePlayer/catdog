package kr.co.catdog.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/naming")
public class NamingController {




    @GetMapping("/place")
    public String NamingPlace(Model model) {
        return "user/naming/petname";
    }
}
