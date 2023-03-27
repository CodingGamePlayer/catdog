package kr.co.catdog.apicontroller;

import kr.co.catdog.dto.GovermentHospitalDTO;
import kr.co.catdog.dto.HospitalDTO;
import kr.co.catdog.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class HospitalApiController {

    private HospitalService hospitalService;
    private String[] mapStrArr = {"hospital_id","address_name","phone", "place_name", "place_url", "road_address_name", "longitude", "latitude"};

    private String[] positionstrArr = {"swLat","swLng","neLat","neLng","centerLat","centerLng"};

    @GetMapping("/api/user/hospital/map")
    public List<HospitalDTO> mapList(@RequestParam Double[] position){
        DecimalFormat df = new DecimalFormat("0.00");
        HashMap<String, Double> locPositino = new HashMap<>();

        for(int idx = 0 ; idx < positionstrArr.length; idx++){
            locPositino.put(positionstrArr[idx], position[idx]);
        }
        List<HospitalDTO> nearList = hospitalService.getNearestHospital(locPositino);

        for (int i = 0; i < nearList.size(); i++) {
            nearList.get(i).setDistance(Double.parseDouble(df.format(nearList.get(i).getDistance())));
        }
        return nearList;
    }

    @GetMapping("/api/user/hospital/initMap")
    public List<HospitalDTO> mapAllList(){
        return hospitalService.getAll();
    }

    @Autowired
    public HospitalApiController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }
}
