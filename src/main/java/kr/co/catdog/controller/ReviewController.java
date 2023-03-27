package kr.co.catdog.controller;

import kr.co.catdog.dto.ReviewDTO;
import kr.co.catdog.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/shop/detail")
public class ReviewController {
    private ReviewService reviewService;

    @PostMapping("/register")
    String register(ReviewDTO reviewDTO) {
        int result = reviewService.insert(reviewDTO);
        return "redirect:/user/shop/detail/" + reviewDTO.getProduct_no();
    }

    @GetMapping("/{product_no}/delete/{review_no}")
    String delete(@PathVariable("product_no") int product_no, @PathVariable("review_no") int review_no) {
        int result = reviewService.delete(review_no);
        return "redirect:/user/shop/detail/" + product_no;
    }
}
