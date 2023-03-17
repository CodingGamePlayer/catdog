package kr.co.catdog.mapper;


import kr.co.catdog.domain.UserVO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest

class UserMapperTest {
@Autowired
UserMapper userMapper;
    @Test
    void selectOne() {
        UserVO userVO = UserVO.builder()
                .user_id("1")
                .user_name("킬러").build();

    }

    @Test
    @Transactional
    void insert() {
        UserVO userVO = UserVO.builder()
                .user_id("fdas")
                .user_pw("1233")
                .user_name("지현")
                .user_image("asdf")
                .user_matchinguse(true)
                .user_phoneNumber("012121515")
                .build();


        Assertions.assertThat(userMapper.insert(userVO)).isEqualTo(0);


    }

    @Test
    void update() {
        UserVO userVO = UserVO.builder()
                .user_id("asdf")
                .user_pw("123")
                .user_name("킬러").build();
        userMapper.update(userVO);
//        Assertions.assertThat(userMapper.update(userVO)).isEqualTo(1);
    }

    @Test
    void delete() {
        UserVO userVO = UserVO.builder()
                .user_id("asdf").build();
        userMapper.delete(userVO);
    }
}