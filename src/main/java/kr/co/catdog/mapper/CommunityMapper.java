package kr.co.catdog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.catdog.dto.CommunityDTO;

@Mapper
public interface CommunityMapper {
	
	List<CommunityDTO> selectAll();
	
	CommunityDTO findByCommunity(CommunityDTO communityDTO);
	
	int register(CommunityDTO communityDTO);
	
	int registerMedia(CommunityDTO communityDTO);
}
