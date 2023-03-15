package kr.co.catdog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/user/profile")
public class UserController {
    @GetMapping("/edit-person")
    String editPerson() {
        return "user/user/edit-person";
    }

    @GetMapping("/edit-pet")
    String editPet() {
        return "user/user/edit-pet";
    }

    @GetMapping("/delete")
    String delete(){
        return "redirect:/";
    }

}
