package kr.co.catdog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/hospital")
public class HospitalController {

    @GetMapping("/search")
    public String HospitalMap() {
            return "user/hospital/map";
    }
}
