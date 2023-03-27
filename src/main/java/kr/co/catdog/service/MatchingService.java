package kr.co.catdog.service;

import kr.co.catdog.dto.PetDTO;

public interface MatchingService {

    PetDTO getMyPet(String user_id);
}
