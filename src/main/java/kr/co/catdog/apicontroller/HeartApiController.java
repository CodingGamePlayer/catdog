package kr.co.catdog.apicontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.catdog.domain.HeartVO;
import kr.co.catdog.service.HeartService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HeartApiController {
	
	@Autowired
	private HeartService heartService;
	
//	좋아요 체크 => 체크해제
	@PutMapping("/api/user/community/like")
	public ResponseEntity<HeartVO> likeFalse(@RequestBody HeartVO heartVO){
		log.info("controller로 넘어온 좋아요 정보 : "+ heartVO);
		int result = heartService.heartUpdate(heartVO);
		
		if(!(result>0)) {
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping("/api/user/community/like")
	public int getLikeCnt(@RequestParam("community_no") int community_no) {
		
		return heartService.getHeartCnt(community_no);
	}

//	좋아요 체크해제 => 체크
	@PostMapping("/api/user/community/like")
	public ResponseEntity<HeartVO> likeDiscrimination(@RequestBody HeartVO heartVO){
		
		int result = heartService.heartDiscrimination(heartVO);
		
		if(!(result>0)) {
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).build();
		
	}

}
