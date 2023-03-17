package kr.co.catdog.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyVO {
	
	private int reply_no;
	private String community_no;
	private String user_id;
	private String reply_content;
	private String reply_regdate;
	
}
