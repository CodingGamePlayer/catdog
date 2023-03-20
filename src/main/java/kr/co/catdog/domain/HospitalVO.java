package kr.co.catdog.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class HospitalVO {
    public int hospital_id;
    public String address_name;
    public String phone;
    public String place_name;
    public String place_url;
    public String road_address_name;
    public double longitude;
    public double latitude;
}
