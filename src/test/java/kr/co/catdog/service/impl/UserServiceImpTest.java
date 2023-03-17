package kr.co.catdog.service.impl;

import kr.co.catdog.domain.UserVO;
import kr.co.catdog.dto.UserDTO;
import kr.co.catdog.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Slf4j
class UserServiceImpTest {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserMapper userMapper;

    @Test
    void selectOne() {
        UserDTO userDTO = UserDTO.builder()
                .user_id("1")
                .build();
//        UserVO userVO = userMapper.selectOne(modelMapper.map(userDTO, UserVO.class));
//        UserDTO userDTO1 = modelMapper.map(userVO, UserDTO.class);
//        log.info(String.valueOf(userVO));
//        log.info(String.valueOf(userDTO1));

    }

    @Test
    void insert() {

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}