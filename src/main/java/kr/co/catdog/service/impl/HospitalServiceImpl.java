package kr.co.catdog.service.impl;

import kr.co.catdog.dto.HospitalDTO;
import kr.co.catdog.dto.UserDTO;
import kr.co.catdog.mapper.HospitalMapper;
import kr.co.catdog.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    HospitalMapper hospitalMapper;


    @Override
    public int insert(HospitalDTO hospitalDTO) {
        return hospitalMapper.insert(hospitalDTO);
    }
}
