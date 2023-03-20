package kr.co.catdog.service.impl;

import kr.co.catdog.dto.HospitalDTO;
import kr.co.catdog.dto.UserDTO;
import kr.co.catdog.mapper.HospitalMapper;
import kr.co.catdog.service.HospitalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospitalServiceImpl implements HospitalService {


    HospitalMapper hospitalMapper;
    private final ModelMapper modelMapper = new ModelMapper();


    @Override
    public int insert(HospitalDTO hospitalDTO) {
        return hospitalMapper.insert(hospitalDTO);
    }

    @Override
    public List<HospitalDTO> getAll() {
        return hospitalMapper.getAll().stream().map(hospitalVO -> modelMapper.map(hospitalVO, HospitalDTO.class)).collect(Collectors.toList());
    }


    @Autowired
    public HospitalServiceImpl(HospitalMapper hospitalMapper) {
        this.hospitalMapper = hospitalMapper;
    }
}
