package kr.co.catdog.apicontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.catdog.domain.ReplyVO;
import kr.co.catdog.dto.ReplyDTO;
import kr.co.catdog.service.ReplyService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ReplyApiController {
	
	@Autowired
	private ReplyService replyService;
	
	@PostMapping("/api/user/community/reply")
	public ResponseEntity<ReplyVO> registerReply(@RequestBody ReplyDTO replyDTO){
		log.info("replyDTO : "+replyDTO);
			int result = replyService.registerReply(replyDTO);
		return  ResponseEntity.status(HttpStatus.OK).build();

	}
	
	@GetMapping("/api/user/community/reply")
	public List<ReplyVO> getReply(@RequestParam("community_no") int community_no, ReplyDTO replyDTO){
		
		replyDTO.setCommunity_no(community_no);
		log.info("댓글 불러오는 replyDTO의 community_no : "+community_no);
		
		return replyService.getReply(replyDTO);
	}

}
