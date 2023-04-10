package kr.co.catdog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private int order_no;
    private String user_id;
    private int order_price;
    private String order_date;
    private String pg_token;
    private String server_name;
}
