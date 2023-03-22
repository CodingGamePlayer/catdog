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

import kr.co.catdog.domain.LikeVO;
import kr.co.catdog.service.LikeService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LikeApiController {
	
	@Autowired
	private LikeService likeService;
	
//	좋아요 체크 => 체크해제
	@PutMapping("/api/user/community/like")
	public ResponseEntity<LikeVO> likeFalse(@RequestBody LikeVO likeVO){
		
		int result = likeService.likeUpdate(likeVO);
		
		if(!(result>0)) {
			ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping("/api/user/community/like")
	public int getLikeCnt(@RequestParam("community_no") int community_no) {
		
		return likeService.getLikeCnt(community_no);
	}

//	좋아요 체크해제 => 체크
	@PostMapping("/api/user/community/like")
	public ResponseEntity<LikeVO> likeDiscrimination(@RequestBody LikeVO likeVO){
		
		int result = likeService.likeDiscrimination(likeVO);
		
		if(!(result>0)) {
			ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).build();
		
	}

}
