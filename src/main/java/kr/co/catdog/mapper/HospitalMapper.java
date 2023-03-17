package kr.co.catdog.mapper;

import kr.co.catdog.domain.HospitalVO;
import kr.co.catdog.domain.UserVO;
import kr.co.catdog.dto.HospitalDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HospitalMapper {

    int insert(HospitalDTO hospitalDTO);
}
