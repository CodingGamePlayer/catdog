package kr.co.catdog.apicontroller;

import kr.co.catdog.dto.ErrorResponse;
import kr.co.catdog.dto.PetDTO;
import kr.co.catdog.dto.UserDTO;
import kr.co.catdog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class AccountApiController {
    private UserService userService;
    @PostMapping("/api/signup")
    public int signup(@RequestBody UserDTO userDTO) {

        UserDTO user = userService.findById(userDTO.getUser_id());
        if(user != null) {
            return 0;
        }
        return userService.insert(userDTO);
    }
    @Autowired
    public AccountApiController(UserService userService) {
        this.userService = userService;
    }
}
