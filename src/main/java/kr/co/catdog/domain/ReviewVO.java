package kr.co.catdog.domain;

import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class ReviewVO {
    private int review_no;
    private String user_id;
    private int product_no;
    private LocalDate review_regdate;
    private int review_score;
    private String review_content;

}
