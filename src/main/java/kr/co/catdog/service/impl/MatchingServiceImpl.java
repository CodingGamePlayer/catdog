package kr.co.catdog.service.impl;

import kr.co.catdog.domain.MatchingVO;
import kr.co.catdog.domain.PetVO;
import kr.co.catdog.dto.MatchingDTO;
import kr.co.catdog.dto.PetDTO;
import kr.co.catdog.mapper.MatchingMapper;
import kr.co.catdog.mapper.PetMapper;
import kr.co.catdog.service.MatchingService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MatchingServiceImpl implements MatchingService {

    ModelMapper modelMapper = new ModelMapper();

    private PetMapper petMapper;

    private MatchingMapper matchingMapper;

    @Override
    public PetDTO getMyPet(String user_id) {

        PetDTO petDTO = modelMapper.map(petMapper.findById(user_id), PetDTO.class);


        return petDTO;
    }

    @Override
    public PetDTO matching(MatchingDTO matchingDTO) {
        List<PetVO> petVOList = petMapper.list(matchingDTO);
        Random random = new Random();
        PetVO petVO;
        int randomIndex = 0;
        int result = 1;
//       과거에 이미 매칭되었던 데이터가 있는지 확인하는 로직
//       이미 매칭 된 전적이 있다면 해당 객체를 제외한 리스트 중 다시 랜덤
        do {
//            조건에 맞는 상대 리스트가 비었는지 확인 후 비어있다면 반복문 탈출
            if(petVOList.isEmpty()){
                petVO = null;
                break;
            }
            randomIndex = random.nextInt(petVOList.size());
            petVO = petVOList.get(randomIndex);
            MatchingVO matchingVO = MatchingVO.builder()
                    .user_id(matchingDTO.getUser_id())
                    .matching_user_id(petVO.getUser_id())
                    .build();
            petVOList.remove(randomIndex);
            result = matchingMapper.findByUserId(matchingVO);
        } while (result == 1 );
//        적합한 상대가 없다면 null 반환
        if(petVO==null){
            return null;
        }
        PetDTO petDTO = modelMapper.map(petVO, PetDTO.class);
//        적합한 랜덤 상대를 찾았다면 matching 테이블에 기록 후 해당 펫정보를 반환
        MatchingVO matchingVO = MatchingVO.builder()
                .user_id(matchingDTO.getUser_id())
                .matching_user_id(petDTO.getUser_id())
                .build();
        matchingMapper.register(matchingVO);

        return petDTO;
    }

    @Override
    public List<MatchingDTO> list(MatchingDTO matchingDTO) {
        List<MatchingVO> matchingVOList = matchingMapper.list(matchingDTO);
        List<MatchingDTO> matchingDTOs = matchingVOList.stream()
                .map(matchingVO -> modelMapper.map(matchingVO, MatchingDTO.class))
                .collect(Collectors.toList());
        for(int i = 0 ; i < matchingDTOs.size() ; i++){
            matchingDTOs.get(i).setData(petMapper.findById(matchingDTOs.get(i).getMatching_user_id()));
        }

        return matchingDTOs;
    }

    @Override
    public int update(MatchingDTO matchingDTO) {


        return matchingMapper.update(matchingDTO);
    }


    @Autowired
    public MatchingServiceImpl(PetMapper petMapper, MatchingMapper matchingMapper) {
        this.petMapper = petMapper;
        this.matchingMapper = matchingMapper;
    }
}
