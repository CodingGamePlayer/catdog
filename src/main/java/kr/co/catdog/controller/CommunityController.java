package kr.co.catdog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("user/community")
public class CommunityController {
	
	@GetMapping("list")
	ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/community/list-community");
		return mav;
	}

}
