package kr.co.catdog.service;

import kr.co.catdog.dto.MatchingDTO;
import kr.co.catdog.dto.PetDTO;

import java.util.List;

public interface MatchingService {

    PetDTO getMyPet(String user_id);

    PetDTO matching(MatchingDTO matchingDTO);

    List<MatchingDTO> list(MatchingDTO matchingDTO);

}
