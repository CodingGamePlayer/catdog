package kr.co.catdog.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.catdog.dto.CommunityDTO;
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
	public List<CommunityDTO> selectAll() {
		
		return communityMapper.selectAll();
	}

	@Override
	public CommunityDTO findByCommunity(CommunityDTO communityDTO) {
		// TODO Auto-generated method stub
		return null;
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
	
	

}
