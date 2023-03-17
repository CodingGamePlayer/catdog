package kr.co.catdog.controller;

import kr.co.catdog.dto.PetDTO;
import kr.co.catdog.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class UserControllerTest {
@Autowired
    PetService petService;
    @Test
    void editPetForm() {
    }

    @Test
    void editPet() {
        PetDTO petDTO = PetDTO.builder()
                .user_id("jh@cdf.com")
                .pet_animalnum(111111111).build();
        int result = petService.update(petDTO);
        log.info(String.valueOf(result));
    }
}