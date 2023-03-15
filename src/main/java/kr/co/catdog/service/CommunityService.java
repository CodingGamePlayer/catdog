package kr.co.catdog.service;

import java.util.List;

import kr.co.catdog.dto.CommunityDTO;

public interface CommunityService {
	
	List<CommunityDTO> selectAll(CommunityDTO communityDTO);
	
	CommunityDTO findByCommunity(CommunityDTO communityDTO);
	
	int register(CommunityDTO communityDTO);
	
	

}
