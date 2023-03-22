package kr.co.catdog.mapper;

import kr.co.catdog.domain.UserVO;
import kr.co.catdog.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    UserVO findById(UserDTO userDTO);

    int insert(UserDTO userDTO);

    int update(UserDTO userDTO);

    int delete(UserDTO userDTO);


}
