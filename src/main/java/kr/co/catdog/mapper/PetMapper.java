package kr.co.catdog.mapper;

import kr.co.catdog.domain.PetVO;
import kr.co.catdog.dto.PetDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PetMapper {
    PetVO findById(PetDTO petDTO);
    int update(PetDTO petDTO);

}
