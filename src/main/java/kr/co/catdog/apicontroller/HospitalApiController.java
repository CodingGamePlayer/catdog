package kr.co.catdog.apicontroller;

import kr.co.catdog.domain.ReplyVO;
import kr.co.catdog.dto.HospitalDTO;
import kr.co.catdog.dto.ReplyDTO;
import kr.co.catdog.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class HospitalApiController {

    private HospitalService hospitalService;

    @GetMapping("/api/user/hospital/map")
    public List<Map<String, Object>> mapList(){
        JSONObject jsonObj;
        JSONArray jsonArr = new JSONArray();

        HashMap<String, Object> hash = new HashMap<>();

        List<HospitalDTO> listAll = hospitalService.getAll();
        for (int i = 0; i < listAll.size(); i++) {
            hash.put("hospital_id", listAll.get(i).getHospital_id());
            hash.put("business_name", listAll.get(i).getBusiness_name());
            hash.put("tel", listAll.get(i).getTel());

            hash.put("region_type_b", listAll.get(i).getRegion_type_b());
            hash.put("address_name_b", listAll.get(i).getAddress_name_b());
            hash.put("region_1depth_name_b", listAll.get(i).getRegion_1depth_name_b());
            hash.put("region_2depth_name_b", listAll.get(i).getRegion_2depth_name_b());
            hash.put("region_3depth_name_b", listAll.get(i).getRegion_3depth_name_b());
            hash.put("region_4depth_name_b", listAll.get(i).getRegion_4depth_name_b());
            hash.put("code_b", listAll.get(i).getCode_b());
            hash.put("longitude_b", listAll.get(i).getLongitude_b());
            hash.put("latitude_b", listAll.get(i).getLatitude_b());
            /*
            hash.put("region_type_h", listAll.get(i).getRegion_type_h());
            hash.put("address_name_h", listAll.get(i).getAddress_name_h());
            hash.put("region_1depth_name_h", listAll.get(i).getRegion_1depth_name_h());
            hash.put("region_2depth_name_h", listAll.get(i).getRegion_2depth_name_h());
            hash.put("region_3depth_name_h", listAll.get(i).getRegion_3depth_name_h());
            hash.put("region_4depth_name_h", listAll.get(i).getRegion_4depth_name_h());
            hash.put("code_h", listAll.get(i).getCode_h());
            hash.put("longitude_h", listAll.get(i).getLongitude_h());
            hash.put("latitude_h", listAll.get(i).getLatitude_h());
            */
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
