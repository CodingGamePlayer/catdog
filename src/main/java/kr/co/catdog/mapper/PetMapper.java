package kr.co.catdog.mapper;

import kr.co.catdog.domain.PetVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PetMapper {
    PetVO findById(String user_id);

//    int insert(String user_id);

    int update(PetVO petVO);

//    int delete(String user_id);

}
