package kr.co.catdog.service.impl;

import kr.co.catdog.domain.PetVO;
import kr.co.catdog.dto.PetDTO;
import kr.co.catdog.mapper.CategoryMapper;
import kr.co.catdog.mapper.PetMapper;
import kr.co.catdog.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PetServiceImp implements PetService {
    @Autowired
    ModelMapper modelMapper;
    private PetMapper petMapper;
    @Autowired
    CategoryMapper categoryMapper;
    public PetServiceImp(PetMapper petMapper) {
        this.petMapper = petMapper;
    }

    @Override
    public PetDTO findById(String user_id) {
        PetDTO petDTO = modelMapper.map(petMapper.findById(user_id), PetDTO.class);
        petDTO.setCategory1VOList(categoryMapper.selectCategory1());
        petDTO.setCategory2VOList(categoryMapper.selectCategory2());
        
        return petDTO;
    }

//    @Override
//    public int insert(String user_id) {
//        int result = petMapper.insert(user_id);
//
//        return !(result > 0) ? 0 : 1;
//    }

    @Override
    public int update(PetDTO petDTO) {

        int result = petMapper.update(modelMapper.map(petDTO, PetVO.class));

        return !(result > 0) ? 0 : 1;
    }

//    @Override
//    public int delete(String user_id) {
//        int result = petMapper.delete(user_id);
//
//        return !(result > 0) ? 0 : 1;
//    }
}
