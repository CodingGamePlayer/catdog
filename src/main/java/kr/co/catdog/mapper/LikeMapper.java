package kr.co.catdog.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.catdog.domain.LikeVO;

@Mapper
public interface LikeMapper {
	
	int likeUpdate(LikeVO likeVO);
	
	int getLikeCnt(LikeVO likeVO);
	
	LikeVO getInfo(LikeVO likeVO);
	
	int likeRegister(LikeVO likeVO);

}
