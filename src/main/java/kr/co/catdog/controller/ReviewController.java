package kr.co.catdog.controller;

import kr.co.catdog.dto.ReviewDTO;
import kr.co.catdog.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/shop")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/review/myreview")
    String myReview(Model model, String user_id) {

        model.addAttribute("myReviewList", reviewService.findByUserid(user_id));

        return "/user/shop/myreview";
    }
    @PostMapping("/review/register")
    String register(ReviewDTO reviewDTO) {
        int result = reviewService.insert(reviewDTO);
        return "redirect:/user/shop/detail/" + reviewDTO.getProduct_no();
    }

    @GetMapping("/{product_no}/review/delete/{review_no}")
    String delete(@PathVariable("product_no") int product_no, @PathVariable("review_no") int review_no) {
        int result = reviewService.delete(review_no);

        return "redirect:/user/shop/detail/" + product_no;
    }
}
