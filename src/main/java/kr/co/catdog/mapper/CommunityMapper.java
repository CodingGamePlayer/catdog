package kr.co.catdog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.catdog.domain.CommunityVO;
import kr.co.catdog.domain.MediaVO;
import kr.co.catdog.dto.CommunityDTO;

@Mapper
public interface CommunityMapper {
	
	List<CommunityVO> selectAll(CommunityVO communityVO);
	
	int register(CommunityDTO communityDTO);
	
	int registerMedia(CommunityDTO communityDTO);
	
	CommunityVO findByCommunity(CommunityVO communityVO);
	
	int update(CommunityVO communityVO);
	
	int updateMedia(MediaVO mediaVO);
	
	int delete(CommunityVO communityVO);

}
