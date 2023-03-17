package kr.co.catdog.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityVO {
	
	private int community_no;
	private String user_id;
	private int category_no;
	private int category_type;
	private String community_title;
	private String community_content;
	private String community_regdate;
	private String community_editdate;
	private int media_type;
	private String media_path;
	private int reply_no;
	private String reply_user_id;
	private String reply_content;
	private String reply_regdate;

}
