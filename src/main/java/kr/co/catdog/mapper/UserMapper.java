package kr.co.catdog.mapper;

import kr.co.catdog.domain.UserVO;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;

@Mapper
public interface UserMapper {


    UserVO selectOne(UserVO userVO);


    int insert(UserVO userVO);

    int update(UserVO userVO);

    int delete(UserVO userVO);


}
