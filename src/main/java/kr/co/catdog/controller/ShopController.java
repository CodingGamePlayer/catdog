package kr.co.catdog.controller;

import groovy.util.logging.Slf4j;
import kr.co.catdog.dto.ProductDTO;
import kr.co.catdog.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/shop")
public class ShopController {
    private final ShopService shopService;


    @GetMapping("/cart")
    String cart() {
        return "user/shop/cart";
    }

    @GetMapping("/list")
    String list(Model model) {
        model.addAttribute("msg", "productRegisterBtn");
        model.addAttribute("productList",shopService.selectAll());

        return "user/shop/list";
    }

    @GetMapping("/detail/{product_no}")
    String detail(@PathVariable("product_no") int product_no, Model model) {
        ProductDTO productDTO = shopService.findById(product_no);
        model.addAttribute("product", productDTO);
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

        return "redirect:/user/shop/list";
    }

    @GetMapping("/edit/{product_no}")
    String editForm(@PathVariable("product_no") int product_no, Model model) {
        ProductDTO productDTO = shopService.findById(product_no);
        model.addAttribute("product", productDTO);

        return "user/shop/edit";
    }

    @PostMapping("/edit")
    String edit(ProductDTO productDTO) {
        int result = shopService.update(productDTO);

        return "redirect:/user/shop/detail/"+productDTO.getProduct_no();
    }

    @GetMapping("/delete/{product_no}")
    String delete(@PathVariable("product_no") int product_no){
        int result = shopService.delete(product_no);

        return "redirect:/user/shop/list";
    }

    @PostMapping("/register/media")
    String insertMedia(ProductDTO productDTO){
        int result= shopService.insertMedia(productDTO);

        return "redirect:/user/shop/edit/"+productDTO.getProduct_no();
    }


}
