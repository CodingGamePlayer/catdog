package kr.co.catdog.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CommunityDTO {
	
	private int community_no;
	private String user_id;
	private int category_no;
	private String category_type;
	private String community_content;
	private String community_regdate;
	private String community_editdate;
	private MultipartFile file;
	private int media_no;
	private int media_type;
	private String media_path;

}
