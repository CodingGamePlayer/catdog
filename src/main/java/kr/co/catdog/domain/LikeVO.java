package kr.co.catdog.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeVO {
	
	private int like_no;
	private int community_no;
	private String user_id;
	private boolean like_boolean;

}
