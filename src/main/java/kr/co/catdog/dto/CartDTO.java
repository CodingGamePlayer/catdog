package kr.co.catdog.dto;

import kr.co.catdog.domain.ProductVO;
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

    private ProductVO productVO;
}
