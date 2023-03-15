package kr.co.catdog.mapper;

import groovy.util.logging.Slf4j;
import kr.co.catdog.domain.UserVO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class UserMapperTest {
@Autowired
UserMapper userMapper;
    @Test
    void selectOne() {
    }

    @Test
    void insert() {
        UserVO userVO = UserVO.builder()
                .user_id("asdf")
                .user_pw("1233")
                .user_name("지현")
                .user_image("asdf")
                .user_matchinguse(true)
                .user_phoneNumber("012121515")
                .build();

        userMapper.insert(userVO);

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}