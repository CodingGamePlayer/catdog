package kr.co.catdog.domain;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class ProductVO {
    private int product_no;
    private String user_id;
    private int category1_no;
    private String product_name;
    private int product_stock;
    private String product_content;
    private int product_price;
    private LocalDate product_regdate;

}

