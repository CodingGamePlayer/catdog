package kr.co.catdog.controller;


import kr.co.catdog.dto.MatchingDTO;
import kr.co.catdog.service.MatchingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class MatchingController {

    @Autowired
    private MatchingService matchingService;
    @GetMapping("/user/matching")
    public ModelAndView showMatching(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("session_id");
        mav.addObject("myPet", matchingService.getMyPet(user_id));
        mav.setViewName("/user/matching/showMatching");
        return mav;
    }

    @PostMapping("/user/matching")
    ModelAndView matching(MatchingDTO matchingDTO){
        ModelAndView mav = new ModelAndView();

        return mav;
    }

}
