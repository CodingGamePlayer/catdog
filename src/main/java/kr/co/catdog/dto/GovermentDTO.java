package kr.co.catdog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GovermentDTO {
    private int hospital_id;
    private String business_name;
    private String tel;
    private Double longitude;
    private Double latitude;
}
