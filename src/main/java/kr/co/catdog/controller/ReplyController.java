package kr.co.catdog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.catdog.dto.ReplyDTO;
import kr.co.catdog.service.ReplyService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	@GetMapping("user/community/myreply")
	public ModelAndView myReply(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("session_id");
		log.info(user_id);
		ReplyDTO replyDTO = ReplyDTO.builder()
									.user_id(user_id)
									.build();
		if(user_id == null) {
			mav.setViewName("sign-in");
		}
		mav.addObject("user_id", user_id);
		mav.addObject("replyVOs", replyService.myReply(user_id));
		mav.setViewName("user/community/list-reply");
		return mav;
	}

}
