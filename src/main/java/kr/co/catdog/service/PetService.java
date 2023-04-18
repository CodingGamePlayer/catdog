package kr.co.catdog.service;

import kr.co.catdog.dto.PetDTO;

public interface PetService {

    PetDTO findById(String user_id);

    int update(PetDTO petDTO);

}
