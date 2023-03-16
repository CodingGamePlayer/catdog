package kr.co.catdog.service.impl;

import kr.co.catdog.domain.PetVO;
import kr.co.catdog.dto.PetDTO;
import kr.co.catdog.mapper.PetMapper;
import kr.co.catdog.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class PetServiceImpTest {
@Autowired
    PetMapper petMapper;
    @Test
    void findById() {
        PetVO petVO = petMapper.findById("jh@cdf.com");
        log.info(String.valueOf(petVO));
    }


    @Test
    void update() {
        PetVO petVO = PetVO.builder()
                .user_id("jh@cdf.com")
                .pet_name("삐삐").build();
        petMapper.update(petVO);
    }


}