package kr.co.catdog.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HeartVO {
	
	private int heart_no;
	private int community_no;
	private String user_id;
	private boolean heart_boolean;

}
