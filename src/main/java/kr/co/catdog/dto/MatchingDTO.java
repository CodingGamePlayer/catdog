package kr.co.catdog.dto;

import kr.co.catdog.domain.MatchingVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Builder
public class MatchingDTO<E> {

    private int matching_no;
    private String user_id;
    private String matching_user_id;
    private int matching_request;
    private int matching_response;
    private int matching_result;
    private int category1_no;
    private int category2_no;
    private int pet_gender;
    private int ageFirst;
    private int ageSecond;
    private Object data;

    public MatchingDTO(Object data){
        this.data = data;
    }



}
