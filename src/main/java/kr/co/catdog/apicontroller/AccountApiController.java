package kr.co.catdog.apicontroller;

import kr.co.catdog.dto.UserDTO;
import kr.co.catdog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class AccountApiController {
    private UserService userService;

    @PostMapping("/api/signup")
    public int signup(@RequestBody UserDTO userDTO) {

        UserDTO user = userService.findById(userDTO.getUser_id());
        if (user != null) {
            return 0;
        }
        return userService.insert(userDTO);
    }

    @Autowired
    public AccountApiController(UserService userService) {
        this.userService = userService;
    }
}
