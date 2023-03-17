package kr.co.catdog.mapper;

import kr.co.catdog.dto.GovermentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GovermentMapper {

    List<GovermentDTO> getAll();
}
