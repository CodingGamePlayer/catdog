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
    PetService petService;
//    @Test
//    void findById() {
//        PetVO petVO = petMapper.findById("jh@cdf.com");
//        log.info(String.valueOf(petVO));
//    }


    @Test
    void update() {
        PetDTO petDTO = PetDTO.builder()
                .user_id("jh@cdf.com")
                .pet_name("삐카츄")
                .pet_animalnum(12345).build();
        petService.update(petDTO);
    }


}