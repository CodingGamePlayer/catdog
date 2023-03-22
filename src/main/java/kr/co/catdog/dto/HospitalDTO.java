package kr.co.catdog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HospitalDTO {
    public int hospital_id;
    public String address_name;
    public String phone;
    public String place_name;
    public String place_url;
    public String road_address_name;
    public double longitude;
    public double latitude;
    public double distance;

    public void floorDistance(Double distance) {
        this.distance = Math.floor((distance * 100) / 100.0);
    }
}
