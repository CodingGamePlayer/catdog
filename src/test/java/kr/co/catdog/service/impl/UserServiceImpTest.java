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
    void insert() {

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}