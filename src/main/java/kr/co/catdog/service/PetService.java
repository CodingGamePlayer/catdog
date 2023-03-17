package kr.co.catdog.service;

import kr.co.catdog.domain.PetVO;
import kr.co.catdog.dto.PetDTO;

public interface PetService {
    PetDTO findById(String user_id);

//    int insert(String user_id);

    int update(PetDTO petDTO);

//    int delete(String user_id);
}
