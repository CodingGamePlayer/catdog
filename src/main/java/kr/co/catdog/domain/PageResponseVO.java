package kr.co.catdog.domain;

import kr.co.catdog.dto.CommunityDTO;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseVO {
    private int loadCount;
    private int startIndex;
    private int endIndex;
    private int size = 5;
    private String user_id;

    @Builder(builderMethodName = "index")
    public PageResponseVO(CommunityDTO communityDTO){
        this.user_id = communityDTO.getUser_id();
        this.loadCount = communityDTO.getLoadCount();

        this.startIndex = size*(loadCount -1);
        this.endIndex = startIndex + size -1 ;
    }
}
