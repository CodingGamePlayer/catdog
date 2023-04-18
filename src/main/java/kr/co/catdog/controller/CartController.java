package kr.co.catdog.controller;

import kr.co.catdog.dto.CartDTO;
import kr.co.catdog.service.CartService;
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
@RequestMapping("/user/shop/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping("/{user_id}")
    String list(@PathVariable String user_id, Model model, @ModelAttribute("warningToastMsg") String warningToastMsg) {
        model.addAttribute("cartList", cartService.findById(user_id));

        return "user/shop/cart";
    }

    @PostMapping("/register")
    String register(CartDTO cartDTO, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        int result = cartService.insert(cartDTO);
        if (result == 0) {
            redirectAttributes.addFlashAttribute("warningToastMsg", "추가에 실패했습니다.");
        } else {
            HttpSession session = request.getSession();
            int cart = cartService.findById(cartDTO.getUser_id()).size();
            session.setAttribute("session_cart", cart);

            redirectAttributes.addFlashAttribute("cartToastMsg", "toast");
        }

        return "redirect:/user/shop/detail/" + cartDTO.getProduct_no();
    }

    @GetMapping("/delete/{cart_no}")
    String delete(@PathVariable int cart_no, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        int result = cartService.delete(cart_no);
        if (result == 0) {
            redirectAttributes.addFlashAttribute("warningToastMsg", "삭제에 실패했습니다.");
        }
        HttpSession session = request.getSession();
        int cart = cartService.findById((String) session.getAttribute("session_id")).size();
        session.setAttribute("session_cart", cart);

        return "redirect:/user/shop/cart/" + session.getAttribute("session_id");
    }
}
