package kr.co.catdog.service.impl;

import kr.co.catdog.domain.UserVO;
import kr.co.catdog.dto.UserDTO;
import kr.co.catdog.mapper.UserMapper;
import kr.co.catdog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserMapper userMapper;
    private final ModelMapper modelMapper;
    @Value("c:\testimg")
    private String uploadPath;

    @Override
    public UserDTO findById(String user_id) {

        UserVO uservo = userMapper.findById(user_id);
        UserDTO dto = modelMapper.map(uservo, UserDTO.class);
        log.info(String.valueOf(dto));
        return dto;

    }

    @Override
    public int insert(UserDTO userDTO) {
        int result = userMapper.insert(modelMapper.map(userDTO, UserVO.class));
        return !(result > 0) ? 0 : 1;
    }

    @Override
    public int update(UserDTO userDTO) {
//        String filename = UUID.randomUUID().toString()+"_"+userDTO.getUser_image();
//        String filePath = uploadPath + "\\" + filename;
//        File dest = new File(filePath);
//        try{
//            userDTO.getFile().transferTo(dest);
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//
//        userDTO.setUser_image((filename));
        int result = userMapper.insert(modelMapper.map(userDTO,UserVO.class));
        log.info(String.valueOf(result));

        return !(result > 0) ? 0 : 1;
    }

    @Override
    public int delete(String user_id) {
        UserDTO userDTO = findById(user_id);
        int result = userMapper.delete(modelMapper.map(userDTO, UserVO.class));
        return !(result > 0) ? 0 : 1;
    }
}
