package kr.co.catdog.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class CartVO {
    private int cart_no;
    private String user_id;
    private int product_no;
    private int cart_quantity;
}
