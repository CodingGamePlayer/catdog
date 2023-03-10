package kr.co.catdog.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.catdog.dto.CommunityDTO;
import kr.co.catdog.service.CommunityService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("user/community")
public class CommunityController {
	
	@Autowired
	private CommunityService communityservice;
	
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
	ModelAndView register(CommunityDTO communityDTO) throws IllegalStateException, IOException {
		ModelAndView mav = new ModelAndView();
		UUID uuid = UUID.randomUUID();
		String fileName = uuid.toString()+communityDTO.getFile().getOriginalFilename();
		String filePath = "C:\\testimg\\"+fileName;
		File dest = new File(filePath);
		communityDTO.getFile().transferTo(dest);
		communityDTO.setMedia_path(filePath);
		log.info("communityDTO : "+communityDTO);
		int result = communityservice.register(communityDTO);
		if(!(result>0)) {
			mav.addObject("msg", "글쓰기 실패!!");
			mav.setViewName("register");
		}else {
			mav.addObject("msg", "글쓰기 성공!!");
			mav.setViewName("redirect:list");
		}
		
		return mav;
	}

}
