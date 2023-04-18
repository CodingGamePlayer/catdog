package kr.co.catdog.controller;

import kr.co.catdog.dto.PetDTO;
import kr.co.catdog.dto.UserDTO;
import kr.co.catdog.service.CategoryService;
import kr.co.catdog.service.PetService;
import kr.co.catdog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/profile")
public class UserController {

    private final UserService userService;
    private final PetService petService;
    private final CategoryService categoryService;

    @GetMapping("/edit-person/{user_id}")
    String editPersonForm(@PathVariable String user_id, Model model, @ModelAttribute("warningToastMsg") String warningToastMsg) {
        model.addAttribute("user", userService.findById(user_id));

        return "user/profile/edit-person";
    }

    @PostMapping("/edit-person")
    String editPerson(UserDTO userDTO, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        log.info("user-edit에서 오는 userDTO : " + userDTO);
        int result = userService.update(userDTO);
        if (result == 0) {
            redirectAttributes.addFlashAttribute("warningToastMsg", "수정에 실패했습니다.");
        }

        UserDTO DTO = userService.findById(userDTO.getUser_id());

        HttpSession session = request.getSession();
        session.setAttribute("session_img", DTO.getUser_image());

        return "redirect:/user/profile/edit-person/" + userDTO.getUser_id();
    }

    @GetMapping("/edit-pet/{user_id}")
    String editPetForm(@PathVariable String user_id, Model model, @ModelAttribute("warningToastMsg") String warningToastMsg) {
        model.addAttribute("category", categoryService.selectAll());
        model.addAttribute("pet", petService.findById(user_id));

        return "user/profile/edit-pet";
    }

    @PostMapping("/edit-pet")
    String editPet(PetDTO petDTO, RedirectAttributes redirectAttributes) {
        int result = petService.update(petDTO);
        if (result == 0) {
            redirectAttributes.addFlashAttribute("warningToastMsg", "수정에 실패했습니다.");
        }

        return "redirect:/user/profile/edit-pet/" + petDTO.getUser_id();
    }

    @GetMapping("/delete/{user_id}")
    String delete(@PathVariable String user_id, RedirectAttributes redirectAttributes) {
        int result = userService.delete(user_id);
        if (result == 0) {
            redirectAttributes.addFlashAttribute("warningToastMsg", "회원탈퇴에 실패했습니다.");
            return "redirect:/";
        }

        return "redirect:/logout";
    }


}
