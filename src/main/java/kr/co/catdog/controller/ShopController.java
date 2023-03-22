package kr.co.catdog.controller;

import kr.co.catdog.dto.CartDTO;
import kr.co.catdog.dto.ProductDTO;
import kr.co.catdog.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/user/shop")
public class ShopController {
    private final ShopService shopService;

    @GetMapping("/list")
    String list(Model model) {
        model.addAttribute("msg", "productRegisterBtn");
        model.addAttribute("productList",shopService.selectAll());

        return "user/shop/list";
    }

    @GetMapping("/detail/{product_no}")
    String detail(@PathVariable int product_no, Model model) {
        log.info("여기"+String.valueOf(product_no));
        model.addAttribute("product", shopService.findById(product_no));
        return "user/shop/detail";
    }

    @GetMapping("/register")
    String registerForm(Model model) {
        model.addAttribute("category",shopService.insertCategory());
        return "user/shop/register";
    }

    @PostMapping("/register")
    String register(ProductDTO productDTO) {
        int result = shopService.insert(productDTO);

        log.info(String.valueOf(result));

        return "redirect:/user/shop/list";
    }

    @GetMapping("/edit/{product_no}")
    String editForm(@PathVariable int product_no, Model model) {
        ProductDTO DTO = shopService.findById(product_no);
        model.addAttribute("product", DTO);

        return "user/shop/edit";
    }

    @PostMapping("/edit")
    String edit(ProductDTO productDTO) {
        int result = shopService.update(productDTO);

        return "redirect:/user/shop/detail/"+productDTO.getProduct_no();
    }

    @GetMapping("/delete/{product_no}")
    String delete(@PathVariable int product_no){

        int result = shopService.delete(product_no);

        return "redirect:/user/shop/list";
    }

//    Start Cart ------------------------------------------------------------------------
    @GetMapping("/cart")
    String cartList(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String user_id = (String)session.getAttribute("session_id");
        model.addAttribute("cartList",shopService.findById_Cart(user_id));

        return "user/shop/cart";
    }
    @PostMapping("/cart/register")
    String register_cart(CartDTO cartDTO, HttpServletRequest request){
        shopService.insert_Cart(cartDTO);

        HttpSession session = request.getSession();
        int cart = shopService.findById_Cart(cartDTO.getUser_id()).size();
        session.setAttribute("session_cart",cart);
        return "redirect:/user/shop/cart";
    }
    @GetMapping("/cart/delete/{cart_no}")
    String delete_cart(@PathVariable int cart_no, HttpServletRequest request){
        shopService.delete_Cart(cart_no);

        HttpSession session = request.getSession();
        int cart = shopService.findById_Cart((String)session.getAttribute("session_id")).size();
        session.setAttribute("session_cart",cart);
        return "redirect:/user/shop/cart";
    }

//    End Cart ------------------------------------------------------------------------

}
