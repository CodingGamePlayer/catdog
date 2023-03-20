package kr.co.catdog.service;

import kr.co.catdog.dto.HospitalDTO;
import kr.co.catdog.dto.UserDTO;

import java.util.List;

public interface HospitalService {

    int insert(HospitalDTO hospitalDTO);

    List<HospitalDTO> getAll();
}
