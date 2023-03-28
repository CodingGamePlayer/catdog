package kr.co.catdog.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("user/naming")
public class NamingController {

    @GetMapping("/place")
    public String NamingPlace(HttpServletRequest request, Model model) {
        String user_id = (String) request.getSession().getAttribute("session_id");
        model.addAttribute("user_id", user_id);
        return "user/naming/petname";
    }
}
