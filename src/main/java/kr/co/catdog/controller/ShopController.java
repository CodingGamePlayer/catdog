package kr.co.catdog.controller;

import kr.co.catdog.dto.ProductDTO;
import kr.co.catdog.service.CategoryService;
import kr.co.catdog.service.ReviewService;
import kr.co.catdog.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final CategoryService categoryService;
    private final ReviewService reviewService;

    @GetMapping("/list")
    String list(Model model) {
        model.addAttribute("registerMsg", "shop");
        model.addAttribute("productList",shopService.selectAll());

        return "user/shop/list";
    }

    @GetMapping("/detail/{product_no}")
    String detail(@PathVariable int product_no, Model model) {
        model.addAttribute("product", shopService.findById(product_no));
        model.addAttribute("review", reviewService.selectAll(product_no));
        return "user/shop/detail";
    }

    @GetMapping("/register")
    String registerForm(Model model) {
        model.addAttribute("category", categoryService.selectAll());
        return "user/shop/register";
    }

    @PostMapping("/register")
    String register(ProductDTO productDTO) {
        int result = shopService.insert(productDTO);

        return "redirect:/user/shop/list";
    }

    @GetMapping("/edit/{product_no}")
    String editForm(@PathVariable int product_no, Model model) {

        model.addAttribute("product", shopService.findById(product_no));
        model.addAttribute("category", categoryService.selectAll());

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


}
