package kr.co.catdog.service;

import java.io.IOException;
import java.util.List;

import kr.co.catdog.domain.CommunityVO;
import kr.co.catdog.domain.ReplyVO;
import kr.co.catdog.dto.CommunityDTO;
import kr.co.catdog.dto.ReplyDTO;
import org.springframework.core.io.Resource;

public interface CommunityService {
	
//	List<CommunityVO> selectAll(CommunityVO communityVO);
	
	int register(CommunityDTO communityDTO) throws IOException;
	
	CommunityDTO findByCommunity(CommunityDTO communityDTO);

	int update(CommunityDTO communityDTO) throws IOException;
	
	int delete(CommunityDTO communityDTO);
	
//	List<CommunityVO> myCommunity(CommunityDTO communityDTO);
	
//	List<CommunityVO> popularPosts(CommunityDTO communityDTO);

	List<CommunityDTO> selectSize(CommunityDTO communityDTO);

	Resource getMedia(String filename);

}
