package kr.co.catdog.service;

import kr.co.catdog.domain.HeartVO;

public interface HeartService {
	
	int heartUpdate(HeartVO heartVO);
	
	int getHeartCnt(int Community_no);
	
	int heartDiscrimination(HeartVO heartVO);

}
