package kr.co.catdog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/community")
public class CommunityController {
	
	@GetMapping("list")
	String list() {
		return "user/community/list-community";
	}

}
