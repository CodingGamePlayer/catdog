package kr.co.catdog.apicontroller;

import kr.co.catdog.dto.ChatGptResponseDto;
import kr.co.catdog.dto.PetDTO;
import kr.co.catdog.service.ChatGptService;
import kr.co.catdog.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;

@RestController
@Slf4j
public class NamingApiController {

    private ChatGptService chatGptService;
    private PetService petService;

    @PostMapping("/api/user/naming/mypetName")
    public ChatGptResponseDto sendQuestion(@RequestBody String param) {
        log.info("check naming request");
        ChatGptResponseDto responseDto= chatGptService.askQuestion(chatGptService.makePrompt(param));
        log.info(responseDto.getChoices().get(0).getText().toString());
        return responseDto;
    }

    @PutMapping("/api/user/naming/updatePetname/{id}")
    public int updateMypetName(@PathVariable("id") String id, @RequestBody Map<String, Object> petData) {
        PetDTO petDTO = petService.findById(id);
        petDTO.setPet_name((String)petData.get("petName"));
        return petService.update(petDTO);
    }

    @Autowired
    public NamingApiController(ChatGptService chatGptService, PetService petService) {
        this.chatGptService = chatGptService;
        this.petService = petService;
    }
}
