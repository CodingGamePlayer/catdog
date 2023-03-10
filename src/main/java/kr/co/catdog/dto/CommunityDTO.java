package kr.co.catdog.dto;

import org.springframework.web.multipart.MultipartFile;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityDTO {
	
	private int community_no;
	private String user_id;
	private int category_no;
	private int category_type;
	private String community_content;
	private String community_regdate;
	private String community_editdate;
	private MultipartFile file;
	private int media_type;
	private String media_path;

}
