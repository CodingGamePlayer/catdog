package kr.co.catdog.controller;

import kr.co.catdog.dto.PetDTO;
import kr.co.catdog.dto.UserDTO;
import kr.co.catdog.service.PetService;
import kr.co.catdog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/profile")
public class UserController {
    @Value("${kr.co.catdog.upload.path}")
    private String upPath;
    private final UserService userService;
    private final PetService petService;

    @GetMapping("/edit-person")
    String editPersonForm(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("session_id");

        UserDTO user = userService.findById(user_id);
        model.addAttribute("user", user);

        log.info(String.valueOf(user));

        return "user/profile/edit-person";
    }

    @PostMapping("/edit-person")
    String editPerson(UserDTO userDTO, HttpServletRequest request) throws IOException {

        String fileName = UUID.randomUUID().toString() + "_" + userDTO.getFile().getOriginalFilename();
        String filePath = upPath + "\\" + fileName;
        File dest = new File(filePath);

        userDTO.getFile().transferTo(dest);
        userDTO.setUser_image(fileName);

        int result = userService.update(userDTO);

        HttpSession session = request.getSession();
        session.setAttribute("session_img", userDTO.getUser_image());

        return "redirect:/user/profile/edit-person";
    }

    @GetMapping("/edit-pet")
    String editPetForm(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("session_id");

        PetDTO pet = petService.findById(user_id);

        model.addAttribute("pet", pet);

        log.info(String.valueOf(pet));

        return "user/profile/edit-pet";
    }

    @PostMapping("/edit-pet")
    String editPet(PetDTO petDTO) {
        int result = petService.update(petDTO);
        log.info(String.valueOf(result));
        return "redirect:/user/profile/edit-pet";
    }

    @GetMapping("/delete")
    String delete(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("session_id");

        int result = userService.delete(user_id);
        log.info(String.valueOf(result));
        return "redirect:/";
    }


}
