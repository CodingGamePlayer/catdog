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
	private String community_title;
	private String community_content;
	private String community_regdate;
	private String community_editdate;

}
