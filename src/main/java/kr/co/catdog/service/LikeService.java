package kr.co.catdog.service;

import kr.co.catdog.domain.LikeVO;

public interface LikeService {
	
	int likeUpdate(LikeVO likeVO);
	
	int getLikeCnt(int Community_no);
	
	int likeDiscrimination(LikeVO likeVO);

}
