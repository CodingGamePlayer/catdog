package kr.co.catdog.controller;

import kr.co.catdog.dto.CartDTO;
import kr.co.catdog.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/shop/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping("/{user_id}")
    String list(@PathVariable String user_id, Model model) {

        model.addAttribute("cartList",cartService.findById(user_id));

        return "user/shop/cart";
    }
    @PostMapping("/register")
    String register(CartDTO cartDTO, HttpServletRequest request){
        cartService.insert(cartDTO);

        HttpSession session = request.getSession();
        int cart = cartService.findById(cartDTO.getUser_id()).size();
        session.setAttribute("session_cart",cart);

        return "redirect:/user/shop/cart/"+session.getAttribute("session_id");
    }
    @GetMapping("/delete/{cart_no}")
    String delete(@PathVariable int cart_no, HttpServletRequest request){
        cartService.delete(cart_no);

        HttpSession session = request.getSession();
        int cart = cartService.findById((String)session.getAttribute("session_id")).size();
        session.setAttribute("session_cart",cart);

        return "redirect:/user/shop/cart/"+session.getAttribute("session_id");
    }
}
