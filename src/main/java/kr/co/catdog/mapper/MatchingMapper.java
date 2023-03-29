package kr.co.catdog.mapper;

import kr.co.catdog.domain.MatchingVO;
import kr.co.catdog.dto.MatchingDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MatchingMapper {

    int findByUserId(MatchingVO matchingVO);

    int register(MatchingVO matchingVO);

    List<MatchingVO> list(MatchingDTO matchingDTO);

    int update(MatchingDTO matchingDTO);

    MatchingVO findByMatchingNo(MatchingDTO matchingDTO);
}
