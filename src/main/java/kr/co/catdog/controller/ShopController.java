package kr.co.catdog.controller;

import kr.co.catdog.dto.ProductDTO;
import kr.co.catdog.service.CategoryService;
import kr.co.catdog.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/shop")
public class ShopController {
    private final ShopService shopService;
    private final CategoryService categoryService;

    @GetMapping("/list")
    String list(Model model, @ModelAttribute("warningToastMsg") String warningToastMsg) {
        model.addAttribute("registerMsg", "shop");
        model.addAttribute("productScoreList", shopService.orderByReviewScore());
        model.addAttribute("category", categoryService.selectAll());

        return "user/shop/list";
    }

    @GetMapping("/detail/{product_no}")
    String detail(@PathVariable int product_no, @ModelAttribute("cartToastMsg") String cartToastMsg, @ModelAttribute("warningToastMsg") String warningToastMsg, Model model) {
        model.addAttribute("product", shopService.findById(product_no));

        return "user/shop/detail";
    }

    @GetMapping("/register")
    String registerForm(Model model) {
        model.addAttribute("category", categoryService.selectAll());

        return "user/shop/register";
    }

    @PostMapping("/register")
    String register(ProductDTO productDTO, RedirectAttributes redirectAttributes) {
        int result = shopService.insert(productDTO);
        if (result == 0) {
            redirectAttributes.addFlashAttribute("warningToastMsg", "등록에 실패했습니다.");
        }

        return "redirect:/user/shop/list";
    }

    @GetMapping("/edit/{product_no}")
    String editForm(@PathVariable int product_no, Model model) {
        model.addAttribute("product", shopService.findById(product_no));
        model.addAttribute("category", categoryService.selectAll());

        return "user/shop/edit";
    }

    @PostMapping("/edit")
    String edit(ProductDTO productDTO, RedirectAttributes redirectAttributes) {
        int result = shopService.update(productDTO);
        if (result == 0) {
            redirectAttributes.addFlashAttribute("warningToastMsg", "수정에 실패했습니다.");
        }

        return "redirect:/user/shop/detail/" + productDTO.getProduct_no();
    }

    @GetMapping("/delete/{product_no}")
    String delete(@PathVariable int product_no, RedirectAttributes redirectAttributes) {
        int result = shopService.delete(product_no);
        if (result == 0) {
            redirectAttributes.addFlashAttribute("warningToastMsg", "삭제에 실패했습니다.");
        }

        return "redirect:/user/shop/list";
    }

}
