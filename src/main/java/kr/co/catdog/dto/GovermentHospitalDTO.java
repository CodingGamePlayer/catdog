package kr.co.catdog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GovermentHospitalDTO {


    private int hospital_id;
    private String business_name;
    private String tel;
    private Double longitude;
    private Double latitude;
    private String region_type_b;
    private String address_name_b;
    private String region_1depth_name_b;
    private String region_2depth_name_b;
    private String region_3depth_name_b;
    private String region_4depth_name_b;
    private String code_b;
    private Double longitude_b;
    private Double latitude_b;

    private String region_type_h;
    private String address_name_h;
    private String region_1depth_name_h;
    private String region_2depth_name_h;
    private String region_3depth_name_h;
    private String region_4depth_name_h;
    private String code_h;
    private Double longitude_h;
    private Double latitude_h;

}
