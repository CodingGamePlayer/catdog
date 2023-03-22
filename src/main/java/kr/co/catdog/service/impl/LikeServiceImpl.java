package kr.co.catdog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.catdog.domain.LikeVO;
import kr.co.catdog.mapper.LikeMapper;
import kr.co.catdog.service.LikeService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LikeServiceImpl implements LikeService {
	
	@Autowired
	private LikeMapper likeMapper;
	
	@Override
	public int likeUpdate(LikeVO likeVO) {
		
		
		return likeMapper.likeUpdate(likeVO);
	}

	@Override
	public int getLikeCnt(int Community_no) {
		
		LikeVO likeVO = LikeVO.builder()
							.community_no(Community_no)
							.build();
		return likeMapper.getLikeCnt(likeVO);
	}

	
//	like 테이블에 로그인 유저와 해당 글에 좋아요를 했던 데이터가 남아있는지 확인
//	남아 있는 정보가 있다면 likeUpdate로 like_boolean값 true로 변경
//	없다면 likeRegister로 새로운 좋아요 정보 등록
	@Override
	public int likeDiscrimination(LikeVO likeVO) {
		if(likeMapper.getInfo(likeVO) == null) {
		
			return likeMapper.likeRegister(likeVO);
		
		}else {
			
			return likeMapper.likeUpdate(likeVO);
		}
	}
	
	

}
