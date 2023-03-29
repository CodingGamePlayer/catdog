package kr.co.catdog.mapper;

import kr.co.catdog.domain.BoardVO;
import kr.co.catdog.domain.GovermentHospitalVO;
import kr.co.catdog.dto.BoardDTO;
import kr.co.catdog.dto.GovermentHospitalDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardVO> getRecentBoard();
    int insert(BoardVO boardVo);
}
