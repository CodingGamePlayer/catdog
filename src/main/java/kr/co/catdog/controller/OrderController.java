package kr.co.catdog.controller;

import kr.co.catdog.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("user/order")
    public ModelAndView list(@RequestParam("user_id") String user_id){
        ModelAndView mav = new ModelAndView();
        mav.addObject("orderDTOs", orderService.findById(user_id));
        mav.setViewName("user/order/list");


        return mav;
    }

}
