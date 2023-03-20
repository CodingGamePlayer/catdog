package kr.co.catdog.mapper;

import kr.co.catdog.domain.GovermentHospitalVO;
import kr.co.catdog.domain.HospitalVO;
import kr.co.catdog.dto.GovermentHospitalDTO;
import kr.co.catdog.dto.HospitalDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HospitalMapper {

    int insert(GovermentHospitalDTO hospitalDTO);

    List<HospitalVO> getAll();
    List<GovermentHospitalVO> getAllGovermentHospital();

    int insertSearchData(HospitalDTO kakaoHospitalDTO);
}
