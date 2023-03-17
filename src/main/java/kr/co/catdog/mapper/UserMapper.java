package kr.co.catdog.mapper;

import kr.co.catdog.domain.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    UserVO findById(String user_id);

    int insert(UserVO userVO);

    int update(UserVO userVO);

    int delete(UserVO userVO);


}
