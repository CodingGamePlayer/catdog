package kr.co.catdog.service;

import java.util.List;

import kr.co.catdog.domain.CommunityVO;
import kr.co.catdog.domain.ReplyVO;
import kr.co.catdog.dto.CommunityDTO;
import kr.co.catdog.dto.ReplyDTO;

public interface CommunityService {
	
	List<CommunityVO> selectAll(CommunityVO communityVO);
	
	int register(CommunityDTO communityDTO);
	
	CommunityDTO findByCommunity(CommunityDTO communityDTO);

	int update(CommunityDTO communityDTO);
	
	int delete(CommunityDTO communityDTO);
	
	List<CommunityVO> myCommunity(CommunityDTO communityDTO);
	
	List<CommunityVO> popularPosts(CommunityDTO communityDTO);

	List<CommunityDTO> selectSize(CommunityDTO communityDTO);

}
