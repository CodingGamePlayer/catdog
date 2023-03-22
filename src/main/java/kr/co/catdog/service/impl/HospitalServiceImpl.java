package kr.co.catdog.service.impl;

import kr.co.catdog.dto.GovermentHospitalDTO;
import kr.co.catdog.dto.HospitalDTO;
import kr.co.catdog.mapper.HospitalMapper;
import kr.co.catdog.service.HospitalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospitalServiceImpl implements HospitalService {


    HospitalMapper hospitalMapper;
    private final ModelMapper modelMapper = new ModelMapper();


    @Override
    public int insert(GovermentHospitalDTO hospitalDTO) {
        return hospitalMapper.insert(hospitalDTO);
    }

    @Override
    public int insertSearchData(HospitalDTO kakaoHospitalDTO) {
        return hospitalMapper.insertSearchData(kakaoHospitalDTO);
    }


    @Override
    public List<GovermentHospitalDTO> getAllGovermentHospital() {
        return hospitalMapper.getAllGovermentHospital().stream().map(govermentHospitalVO -> modelMapper.map(govermentHospitalVO, GovermentHospitalDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<HospitalDTO> getAll(HashMap<String, Double> locPosition) {
        return hospitalMapper.getAll(locPosition).stream().map(hospitalVO -> modelMapper.map(hospitalVO, HospitalDTO.class)).collect(Collectors.toList());
    }


    @Autowired
    public HospitalServiceImpl(HospitalMapper hospitalMapper) {
        this.hospitalMapper = hospitalMapper;
    }
}
