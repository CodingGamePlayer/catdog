package kr.co.catdog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageDTO {
    private final int size = 10;
    private int startIndex;

    // product detail
    private int product_no;

    // product list
    private int category1_no;
    private String category_sort;
    private String keyword;


}
