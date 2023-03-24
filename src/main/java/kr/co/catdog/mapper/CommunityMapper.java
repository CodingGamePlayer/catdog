package kr.co.catdog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.catdog.domain.CommunityVO;
import kr.co.catdog.domain.MediaVO;
import kr.co.catdog.dto.CommunityDTO;

@Mapper
public interface CommunityMapper {
	
//	전체 리스트
	List<CommunityVO> selectAll(CommunityVO communityVO);
	
//	글 등록
	int register(CommunityDTO communityDTO);
	
//	미디어 등록
	int registerMedia(CommunityDTO communityDTO);
	
//	수정화면 이동 하기 위해 해당글 정보 찾기
	CommunityVO findByCommunity(CommunityVO communityVO);
	
//	글 수정된 정보등록
	int update(CommunityVO communityVO);
	
//	미디어 정보 수정
	int updateMedia(MediaVO mediaVO);
	
//	삭제 요청
	int delete(CommunityVO communityVO);
	
//	내가 쓴글 리스트
	List<CommunityVO> myCommunity(CommunityVO communityVO);
	
//	인기글 검색 (1 순위 좋아요 내림차순, 2순위 가장 최근에 써진 글 내림차순) 
	List<CommunityVO> popularPosts(CommunityVO communityVO);

}
