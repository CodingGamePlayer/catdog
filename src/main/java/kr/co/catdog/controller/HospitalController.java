package kr.co.catdog.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/hospital")
public class HospitalController {

    @Value("${kakao.map.api.key}")
    private String apiKey;


    @GetMapping("/search")
    public String HospitalMap(Model model) {
        model.addAttribute("appKey", apiKey);
        return "user/hospital/map";
    }
}
