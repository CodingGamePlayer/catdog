package kr.co.catdog.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class ProductVO {
    private int product_no;
    private int category1_no;
    private String product_name;
    private int product_stock;
    private String product_image;
    private String product_content;
    private int product_price;
    private LocalDate product_regdate;

}

