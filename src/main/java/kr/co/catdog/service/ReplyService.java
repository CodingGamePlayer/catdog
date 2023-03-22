package kr.co.catdog.service;

import java.util.List;

import kr.co.catdog.domain.ReplyVO;
import kr.co.catdog.dto.ReplyDTO;

public interface ReplyService {
	
	List<ReplyVO> getReply(ReplyDTO replyDTO);
	
	int registerReply(ReplyDTO replyDTO);
	
	int replyCount(int community_no);
	
	int replyDelete(int reply_no);
	
}
