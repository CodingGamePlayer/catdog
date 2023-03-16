package kr.co.catdog.controller;

import kr.co.catdog.dto.UserDTO;
import kr.co.catdog.mapper.UserMapper;
import kr.co.catdog.service.UserService;
import kr.co.catdog.service.impl.UserServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/user/profile")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/edit-person")
    String editPersonForm(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("loginInfo");

        UserDTO user = userService.findById(user_id);
        model.addAttribute("user", user);

        log.info(String.valueOf(user));

        return "user/profile/edit-person";
    }

    @PostMapping("/edit-person")
    String editPerson(UserDTO userDTO) {
        int result = userService.update(userDTO);
        log.info(String.valueOf(result));
        return "redirect:/user/profile/edit-person";
    }

    @GetMapping("/edit-pet")
    String editPetForm() {
        return "user/profile/edit-pet";
    }


    @GetMapping("/delete")
    String delete(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("loginInfo");

        int result = userService.delete(user_id);
        log.info(String.valueOf(result));
        return "redirect:/";
    }


}
