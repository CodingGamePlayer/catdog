package kr.co.catdog.mapper;

import kr.co.catdog.domain.UserVO;
import kr.co.catdog.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserVO login(UserDTO userDTO);
    UserVO findById(String user_id);
    UserVO imgFindById(String user_id);
    int insert(UserDTO userDTO);

    int update(UserDTO userDTO);

    int delete(String user_id);


}
