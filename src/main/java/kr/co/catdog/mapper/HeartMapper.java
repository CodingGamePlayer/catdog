package kr.co.catdog.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.catdog.domain.HeartVO;

@Mapper
public interface HeartMapper {
	
	int heartUpdate(HeartVO heartVO);
	
	int getHeartCnt(HeartVO heartVO);
	
	HeartVO getInfo(HeartVO heartVO);
	
	int heartRegister(HeartVO heartVO);

}
