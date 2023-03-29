package kr.co.catdog.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchingVO {

    private int matching_no;
    private String user_id;
    private String matching_user_id;
    private int matching_request;
    private int matching_response;
    private int matching_result;
}
