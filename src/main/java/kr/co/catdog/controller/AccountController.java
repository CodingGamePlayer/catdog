package kr.co.catdog.controller;

import kr.co.catdog.dto.UserDTO;
import kr.co.catdog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;

    @GetMapping("/login")
    String loginForm() {
        return "sign-in";
    }

    @PostMapping("/login")
    String login(UserDTO userDTO, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        int loginResult = userService.login(userDTO, request);
        if(loginResult == 0){
            redirectAttributes.addFlashAttribute("warningToastMsg", "로그인에 실패했습니다.");
            return "redirect:/login";
        }
        redirectAttributes.addFlashAttribute("successToastMsg", "로그인에 성공하였습니다.");
//        id없거나 비밀번호다를때 알려줘
        return "redirect:/";
    }

    @GetMapping("/logout")
    String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

    @GetMapping("/signup")
    String signupForm() {
        return "sign-up";
    }

    @PostMapping("/signup")
    String signup(UserDTO userDTO) {
        int result = userService.insert(userDTO);

        return "redirect:/login";
    }

    @GetMapping("/g")
    String g(){
        return "toasts";
    }


}
