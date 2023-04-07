package kr.co.catdog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/hospital")
@ControllerAdvice
public class HospitalController {


    private ApplicationContext context;

    @GetMapping("/search")
    public String HospitalMap(Model model) {
        Environment env = context.getEnvironment();
        String apiKey = env.getProperty("KAKAO_MAP_API_KEY");
        model.addAttribute("appKey", apiKey);
        return "user/hospital/map";
    }

    @Autowired
    public HospitalController(ApplicationContext context) {
        this.context = context;
    }
}
