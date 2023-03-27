package kr.co.catdog.service.impl;

import kr.co.catdog.domain.UserVO;
import kr.co.catdog.dto.UserDTO;
import kr.co.catdog.mapper.CartMapper;
import kr.co.catdog.mapper.PetMapper;
import kr.co.catdog.mapper.UserMapper;
import kr.co.catdog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    @Value("${kr.co.catdog.upload.path}")
    private String upPath;
    private final UserMapper userMapper;
    private final ModelMapper modelMapper;
    private final PetMapper petMapper;
    private final CartMapper cartMapper;

    @Override
    public void login(UserDTO userDTO, HttpServletRequest request) {
        UserVO uservo = userMapper.login(userDTO);

        HttpSession session = request.getSession();
        session.setAttribute("session_id", uservo.getUser_id());
        session.setAttribute("session_name", uservo.getUser_name());
        session.setAttribute("session_img", uservo.getUser_image());
        int cart = cartMapper.findById(uservo.getUser_id()).size();
        session.setAttribute("session_cart", cart);

    }

    @Override
    public UserDTO findById(String user_id) {
        UserVO uservo = userMapper.findById(user_id);
        UserDTO dto = modelMapper.map(uservo, UserDTO.class);

        return dto;

    }

    @Override
    public int insert(UserDTO userDTO) {
        int result = userMapper.insert(userDTO);
        petMapper.insert(userDTO); //회원가입시 펫등록됨

        return !(result > 0) ? 0 : 1;
    }

    @Override
    public int update(UserDTO userDTO) {
        String fileName = UUID.randomUUID().toString() + "_" + userDTO.getFile().getOriginalFilename();
        String filePath = upPath + "\\" + fileName;
        File dest = new File(filePath);

        try {
            userDTO.getFile().transferTo(dest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        userDTO.setUser_image(fileName);

        int result = userMapper.update(userDTO);

        return !(result > 0) ? 0 : 1;
    }

    @Override
    public int delete(String user_id) {
        int result = userMapper.delete(user_id);

        return !(result > 0) ? 0 : 1;
    }
}
