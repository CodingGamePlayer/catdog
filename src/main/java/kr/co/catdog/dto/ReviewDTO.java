package kr.co.catdog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDTO {
    private int review_no;
    private String user_id;
    private int product_no;
    private LocalDate review_regdate;
    private String review_content;
    private String user_image;
}
