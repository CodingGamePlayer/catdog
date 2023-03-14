package kr.co.catdog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.catdog.dto.CommunityDTO;

@Controller
@RequestMapping("user/community")
public class CommunityController {
	
	@GetMapping("list")
	ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "cnBtn");
		mav.setViewName("/user/community/list-community");
		return mav;
	}
	
	@GetMapping("detail")
	ModelAndView detail() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/community/detail");
		return mav;
	}
	
	@GetMapping("register")
	ModelAndView register() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/community/register");
		return mav;
	}
	
	@PostMapping("register")
	ModelAndView register(CommunityDTO communityDTO) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:register");
		return mav;
	}

}
