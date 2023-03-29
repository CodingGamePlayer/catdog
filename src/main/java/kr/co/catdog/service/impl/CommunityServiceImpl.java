package kr.co.catdog.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.catdog.domain.CommunityVO;
import kr.co.catdog.domain.MediaVO;
import kr.co.catdog.domain.ReplyVO;
import kr.co.catdog.dto.CommunityDTO;
import kr.co.catdog.dto.ReplyDTO;
import kr.co.catdog.mapper.CommunityMapper;
import kr.co.catdog.service.CommunityService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommunityServiceImpl implements CommunityService {
	
	@Autowired
	private CommunityMapper communityMapper;
	
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<CommunityVO> selectAll(CommunityVO communityVO) {
		List<CommunityVO> comList = communityMapper.selectAll(communityVO);
		
		return comList;
	}

	@Override
	public int register(CommunityDTO communityDTO) {
		int result = communityMapper.register(communityDTO);
		log.info("db다녀온 communityDTO : "+communityDTO);
		int comNo = communityDTO.getCommunity_no();
		if(!(result>0)) {
			return 0;
		}else {
			if( comNo == 0) {
				return 0;
			}else {
				return communityMapper.registerMedia(communityDTO);
			}
		}
	}

	@Override
	public CommunityDTO findByCommunity(CommunityDTO communityDTO) {
		
		CommunityVO communityVO = CommunityVO.builder()
											.community_no(communityDTO.getCommunity_no())
											.build();
		
		return modelMapper.map(communityMapper.findByCommunity(communityVO), CommunityDTO.class);
	}

	@Override
	public int update(CommunityDTO communityDTO) {
		CommunityVO communityVO = CommunityVO.builder()
											.community_no(communityDTO.getCommunity_no())
											.category_type(communityDTO.getCategory_type())
											.community_content(communityDTO.getCommunity_content())
											.build();
		int result = communityMapper.update(communityVO);
		log.info("update 다녀온 result : "+result);
		if(!(result>0)) {
			return 0;
		}
		
		if(!communityDTO.getFile().isEmpty()) {
			MediaVO media = MediaVO.builder()
								.media_no(communityDTO.getMedia_no())
								.media_path(communityDTO.getMedia_path())
								.build();
		int mResult = communityMapper.updateMedia(media);
		log.info("updateMedia 다녀온 mResult : "+mResult);
		
		}
		return 1;
		
		
	}

	@Override
	public int delete(CommunityDTO communityDTO) {
		
		CommunityVO communityVO = CommunityVO.builder()
											.community_no(communityDTO.getCommunity_no())
											.build();
		int result = communityMapper.delete(communityVO);
		
		if(!(result>0)) {
			return 0;
		}
		
		return 1;
	}

	@Override
	public List<CommunityVO> myCommunity(CommunityDTO communityDTO) {
		
		CommunityVO communityVO = CommunityVO.builder()
											.user_id(communityDTO.getUser_id())
											.build();
		
		
		return communityMapper.myCommunity(communityVO);
	}

	@Override
	public List<CommunityVO> popularPosts(CommunityDTO communityDTO) {
		
		CommunityVO communityVO = CommunityVO.builder()
											.user_id(communityDTO.getUser_id())
											.build();
		
		
		return communityMapper.popularPosts(communityVO);
	}



	
	

}
