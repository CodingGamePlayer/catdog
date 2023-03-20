package kr.co.catdog.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class GovermentHospitalVO {
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
