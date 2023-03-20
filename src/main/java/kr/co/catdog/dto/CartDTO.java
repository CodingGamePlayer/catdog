package kr.co.catdog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDTO {
    private int cart_no;
    private String user_id;
    private int product_no;
    private int cart_quantity;
    private String product_name;
    private int product_price;
}
