package kr.co.catdog.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.catdog.domain.CommunityVO;
import kr.co.catdog.dto.CommunityDTO;
import kr.co.catdog.service.CommunityService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/user/community")
public class CommunityController {
	
	@Value("${kr.co.catdog.upload.path}")
	private String upPath;
	
	@Autowired
	private CommunityService communityservice;
	
//	글 모든 리스트 출력
	@GetMapping("list")
	ModelAndView list(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("session_id");
		log.info("로그인 아이디 : "+ user_id);
		CommunityVO communityVO = CommunityVO.builder()
											.user_id(user_id)
											.build();
		mav.addObject("registerMsg", "community");
		mav.addObject("communityVOs", communityservice.selectAll(communityVO));
		mav.addObject("user_id", user_id);
		mav.setViewName("/user/community/list-community");
		return mav;
	}
//	
	@GetMapping("detail")
	ModelAndView detail() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/community/detail");
		return mav;
	}
//	글 등록 화면으로 이동
	@GetMapping("register")
	ModelAndView register(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("session_id");
		if(user_id == null) {
			mav.setViewName("/sign-in");
		}else {
		mav.addObject("user_id", user_id);
		
		mav.setViewName("/user/community/register");
		}
		return mav;
	}
//	글 등록 파일 업로드
	@PostMapping("register")
	ModelAndView register(CommunityDTO communityDTO) throws IllegalStateException, IOException {
		ModelAndView mav = new ModelAndView();
		UUID uuid = UUID.randomUUID();
		String fileName = uuid.toString()+communityDTO.getFile().getOriginalFilename();
		String filePath = upPath + "\\" + fileName;
		File dest = new File(filePath);
		communityDTO.getFile().transferTo(dest);
		communityDTO.setMedia_path(fileName);
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
//	글 수정 화면 이동
	@GetMapping("update")
	ModelAndView updateForm(CommunityDTO communityDTO, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String referer = request.getHeader("Referer");
		mav.addObject("referer", referer);
		mav.addObject("communityDTO", communityservice.findByCommunity(communityDTO));
		mav.setViewName("/user/community/updateForm");
		
		return mav;
	}
//	글 수정하기
	@PostMapping("update")
	ModelAndView update(CommunityDTO communityDTO, @RequestParam("referer") String referer) throws IllegalStateException, IOException {
		ModelAndView mav = new ModelAndView();
		log.info("수정 dTO : "+communityDTO);
		if(!communityDTO.getFile().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			String fileName = uuid.toString()+communityDTO.getFile().getOriginalFilename();
			String filePath = upPath + "\\" + fileName;
			File dest = new File(filePath);
			communityDTO.getFile().transferTo(dest);
			communityDTO.setMedia_path(fileName);
			communityservice.update(communityDTO);
			mav.setViewName("redirect:"+referer);
			log.info("1 실행 됨!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}else {
			communityservice.update(communityDTO);
			mav.setViewName("redirect:"+referer);
			log.info("2 실행 됨!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			
		}
		return mav;
	}
//	글 삭제
	@GetMapping("delete")
	ModelAndView delete(CommunityDTO communityDTO, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		communityservice.delete(communityDTO);
		// 이전 페이지의 url을 가져오는 작업!!
		String referer = request.getHeader("Referer");
		mav.setViewName("redirect:"+referer);
		return mav;
		
	}
//	내가 쓴글 불러오기
	@GetMapping("mycommunity")
	ModelAndView myCommunity(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("session_id");
		if(user_id == null) {
			mav.setViewName("/sign-in");
		}
		CommunityDTO communityDTO = CommunityDTO.builder()
												.user_id(user_id)
												.build();
		List<CommunityVO> communityVO = communityservice.myCommunity(communityDTO);
		log.info("db다녀오 리스트 : "+communityVO);
		mav.addObject("communityVOs", communityservice.myCommunity(communityDTO));
		mav.addObject("user_id", user_id);
		mav.setViewName("/user/community/list-community");
		return mav;
	}
	
	@GetMapping("popularposts")
	ModelAndView popularPosts(CommunityDTO communityDTO, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("session_id");
		log.info("인기글 가는 유저 아이디"+user_id);
		communityDTO.setUser_id(user_id);
		mav.addObject("communityVOs", communityservice.popularPosts(communityDTO));
		mav.addObject("user_id", user_id);
		mav.setViewName("/user/community/list-community");
		return mav;
	}

}
