package kr.co.catdog.mapper;

import kr.co.catdog.domain.PetVO;
import kr.co.catdog.dto.MatchingDTO;
import kr.co.catdog.dto.PetDTO;
import kr.co.catdog.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetMapper {
    PetVO findById(String user_id);

    int insert(UserDTO userDTO);

    int update(PetDTO petDTO);

    List<PetVO> list(MatchingDTO matchingDTO);

}
