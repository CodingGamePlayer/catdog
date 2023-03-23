package kr.co.catdog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.catdog.domain.ReplyVO;
import kr.co.catdog.dto.ReplyDTO;
import kr.co.catdog.mapper.ReplyMapper;
import kr.co.catdog.service.ReplyService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyMapper replyMapper;

	@Override
	public List<ReplyVO> getReply(ReplyDTO replyDTO) {
		ReplyVO replyVO = ReplyVO.builder()
									.community_no(replyDTO.getCommunity_no())
									.build();
		log.info("리스트 불러오는 ReplyVO : "+replyVO);
		List<ReplyVO> relist = replyMapper.getReply(replyVO);
		log.info("리플 리스트 : "+relist);
		
		return relist;
	}
	
	@Override
	public int registerReply(ReplyDTO replyDTO) {
		ReplyVO replyVO = ReplyVO.builder()
									.community_no(replyDTO.getCommunity_no())
									.reply_content(replyDTO.getReply_content())
									.user_id(replyDTO.getUser_id())
									.build();
		log.info("replyVO : "+replyVO );
		int result= replyMapper.registerReply(replyVO);
		if(!(result>0)) {
			return 0;
		}
		
		return 1;
	}

	@Override
	public int replyCount(int community_no) {
		ReplyVO replyVO = ReplyVO.builder()
								.community_no(community_no)
								.build();
		return replyMapper.replyCount(replyVO);
	}

	@Override
	public int replyDelete(int reply_no) {
		ReplyVO replyVO = ReplyVO.builder()
								.reply_no(reply_no)
								.build();
		return replyMapper.replyDelete(replyVO);
	}

	@Override
	public List<ReplyVO> myReply(String user_id) {
		ReplyVO replyVO = ReplyVO.builder()
								.user_id(user_id)
								.build();
		
		return replyMapper.myReply(replyVO);
	}

	
	
}
