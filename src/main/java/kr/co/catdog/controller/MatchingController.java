package kr.co.catdog.controller;


import kr.co.catdog.dto.MatchingDTO;
import kr.co.catdog.dto.PetDTO;
import kr.co.catdog.service.MatchingService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView matching(MatchingDTO matchingDTO){
        log.info("매칭뷰에서 넘어온 DTO : "+matchingDTO);
        ModelAndView mav = new ModelAndView();
        PetDTO petDTO = matchingService.matching(matchingDTO);
        if(petDTO != null) {
            mav.addObject("myPet", matchingService.getMyPet(matchingDTO.getUser_id()));
            mav.addObject("matchingPet", petDTO);
            mav.setViewName("/user/matching/resultMatching");
        }else{
            mav.addObject("myPet", matchingService.getMyPet(matchingDTO.getUser_id()));
            mav.setViewName("/user/matching/failMatching");
        }

        return mav;
    }

    @GetMapping("/user/matching/list")
    public ModelAndView list(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("session_id");
        MatchingDTO matchingDTO = MatchingDTO.builder()
                .user_id(user_id)
                .build();
        mav.addObject("user_id",user_id);
        mav.addObject("matchingDTOs",matchingService.list(matchingDTO));
        mav.setViewName("/user/matching/list");
        return mav;
    }

    @GetMapping("/user/matching/result-matching")
    public ModelAndView resultMatching(MatchingDTO matchingDTO){
        ModelAndView mav = new ModelAndView();
        log.info("매칭 리스트에서 넘어온 DTO : "+matchingDTO);
        mav.addObject("myPet",matchingService.getMyPet(matchingDTO.getUser_id()));
        mav.addObject("matchingPet",matchingService.getMyPet(matchingDTO.getMatching_user_id()));
        mav.addObject("matching_no",matchingDTO.getMatching_no());
        mav.setViewName("/user/matching/resultMatching");

        return mav;
    }

    @PostMapping("/user/matching/update")
    public String update(MatchingDTO matchingDTO){
        log.info("매칭 리절트에서 넘어온 matchingDTO : "+matchingDTO);
        matchingService.update(matchingDTO);
        log.info("매칭 업데이트까지 완료");

        return "redirect:list";

    }

}
