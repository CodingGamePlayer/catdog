package kr.co.catdog.service;

import kr.co.catdog.dto.MatchingDTO;
import kr.co.catdog.dto.PetDTO;

import java.util.List;

public interface MatchingService {

    PetDTO getMyPet(String user_id);

    MatchingDTO matching(MatchingDTO matchingDTO);

    List<MatchingDTO> list(MatchingDTO matchingDTO);

    int update(MatchingDTO matchingDTO);

    MatchingDTO getPet(MatchingDTO matchingDTO);

    int effectiveness(String user_id);

}
