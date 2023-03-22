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

    @GetMapping("/api/user/hospital/map")
    public List<Map<String, Object>> mapList(@RequestParam Double[] position){

        DecimalFormat df = new DecimalFormat("0.00");

        JSONObject jsonObj;
        JSONArray jsonArr = new JSONArray();
        HashMap<String, Double> locPositino = new HashMap<>();
        locPositino.put("swLat", position[0]);
        locPositino.put("swLng", position[1]);
        locPositino.put("neLat", position[2]);
        locPositino.put("neLng", position[3]);
        locPositino.put("centerLat", position[4]);
        locPositino.put("centerLng", position[5]);
        HashMap<String, Object> hash = new HashMap<>();



        List<HospitalDTO> listAll = hospitalService.getAll(locPositino);
        for (int i = 0; i < listAll.size(); i++) {
            hash.put("hospital_id", listAll.get(i).getHospital_id());
            hash.put("address_name", listAll.get(i).getAddress_name());
            hash.put("phone", listAll.get(i).getPhone());
            hash.put("place_name", listAll.get(i).getPlace_name());
            hash.put("place_url", listAll.get(i).getPlace_url());
            hash.put("road_address_name", listAll.get(i).getRoad_address_name());
            hash.put("longitude", listAll.get(i).getLongitude());
            hash.put("latitude", listAll.get(i).getLatitude());
            hash.put("distance", df.format(listAll.get(i).getDistance()));
            jsonObj = new JSONObject(hash);
            jsonArr.add(jsonObj);
        }
        return jsonArr;
    }

    @Autowired
    public HospitalApiController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }
}
