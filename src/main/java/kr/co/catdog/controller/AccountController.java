package kr.co.catdog.controller;

import kr.co.catdog.dto.UserDTO;
import kr.co.catdog.service.CartService;
import kr.co.catdog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;
    private final CartService cartService;

    @GetMapping("/login")
    String signinForm() {
        return "sign-in";
    }

    @PostMapping("/login")
    String signin(UserDTO userDTO, HttpServletRequest request) {
        UserDTO user = userService.findById(userDTO.getUser_id());
//        id없거나 비밀번호다를때 알려줘

        if (user.getUser_pw().equals(userDTO.getUser_pw())) {
            HttpSession session = request.getSession();
            session.setAttribute("session_id", user.getUser_id());
            session.setAttribute("session_name",user.getUser_name());
            session.setAttribute("session_img",user.getUser_image());
            int cart = cartService.findById(user.getUser_id()).size();
            session.setAttribute("session_cart",cart);
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if(session!=null){
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


}
