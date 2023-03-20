package kr.co.catdog.service;

import kr.co.catdog.dto.GovermentHospitalDTO;
import kr.co.catdog.dto.HospitalDTO;

import java.util.List;

public interface HospitalService {

    int insert(GovermentHospitalDTO hospitalDTO);

    int insertSearchData(HospitalDTO kakaoHospitalDTO);

    List<GovermentHospitalDTO> getAllGovermentHospital();
    List<HospitalDTO> getAll();
}
