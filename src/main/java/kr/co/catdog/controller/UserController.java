package kr.co.catdog.controller;

import kr.co.catdog.dto.PetDTO;
import kr.co.catdog.dto.UserDTO;
import kr.co.catdog.mapper.UserMapper;
import kr.co.catdog.service.PetService;
import kr.co.catdog.service.UserService;
import kr.co.catdog.service.impl.UserServiceImp;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/user/profile")
public class UserController {

    private final UserService userService;

    private final PetService petService;


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
    String editPetForm(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("loginInfo");

        PetDTO pet = petService.findById(user_id);
        model.addAttribute("pet", pet);

        log.info(String.valueOf(pet));

        return "user/profile/edit-pet";
    }

    @PostMapping("/edit-pet")
    String editPet(PetDTO petDTO){
        int result = petService.update(petDTO);
        log.info(String.valueOf(result));
        return "redirect:/user/profile/edit-pet";
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
