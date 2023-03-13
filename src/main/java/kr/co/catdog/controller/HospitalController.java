package kr.co.catdog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HospitalController {

    @GetMapping("/hospital")
    public String hospital() {
        return "hospital";
    }
}
