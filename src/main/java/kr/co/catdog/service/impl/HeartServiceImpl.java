package kr.co.catdog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.catdog.domain.HeartVO;
import kr.co.catdog.mapper.HeartMapper;
import kr.co.catdog.service.HeartService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HeartServiceImpl implements HeartService {
	
	@Autowired
	private HeartMapper heartMapper;
	
	@Override
	public int heartUpdate(HeartVO heartVO) {
		
		
		return heartMapper.heartUpdate(heartVO);
	}

	@Override
	public int getHeartCnt(int Community_no) {
		
		HeartVO heartVO = HeartVO.builder()
							.community_no(Community_no)
							.build();
		return heartMapper.getHeartCnt(heartVO);
	}

	
//	like 테이블에 로그인 유저와 해당 글에 좋아요를 했던 데이터가 남아있는지 확인
//	남아 있는 정보가 있다면 likeUpdate로 like_boolean값 true로 변경
//	없다면 likeRegister로 새로운 좋아요 정보 등록
	@Override
	public int heartDiscrimination(HeartVO heartVO) {
		if(heartMapper.getInfo(heartVO) == null) {
		
			return heartMapper.heartRegister(heartVO);
		
		}else {
			
			return heartMapper.heartUpdate(heartVO);
		}
	}
	
	

}
