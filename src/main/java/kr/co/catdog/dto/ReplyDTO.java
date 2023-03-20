package kr.co.catdog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyDTO {
	private int reply_no;
	private int community_no;
	private String user_id;
	private String reply_content;
	private String reply_regdate;
}
