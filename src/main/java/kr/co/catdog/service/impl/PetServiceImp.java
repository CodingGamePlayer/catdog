package kr.co.catdog.service.impl;

import kr.co.catdog.domain.PetVO;
import kr.co.catdog.dto.PetDTO;
import kr.co.catdog.mapper.PetMapper;
import kr.co.catdog.service.PetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PetServiceImp implements PetService {
    private final ModelMapper modelMapper;
    private final PetMapper petMapper;

    @Override
    public PetDTO findById(String user_id) {

        PetVO petVO = petMapper.findById(user_id);
        PetDTO petDTO = modelMapper.map(petVO, PetDTO.class);
        
        return petDTO;
    }

    @Override
    public int update(PetDTO petDTO) {

        int result = petMapper.update(petDTO);

        return !(result > 0) ? 0 : 1;
    }


}
