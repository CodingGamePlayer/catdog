package kr.co.catdog.service.impl;

import kr.co.catdog.dto.PetDTO;
import kr.co.catdog.mapper.PetMapper;
import kr.co.catdog.service.MatchingService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.modelmbean.ModelMBean;

@Service
@Slf4j
public class MatchingServiceImpl implements MatchingService {

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private PetMapper petMapper;
    @Override
    public PetDTO getMyPet(String user_id) {

        PetDTO petDTO = modelMapper.map(petMapper.findById(user_id), PetDTO.class);


        return petDTO;
    }
}
