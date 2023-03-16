package kr.co.catdog.service.impl;

import kr.co.catdog.domain.UserVO;
import kr.co.catdog.dto.UserDTO;
import kr.co.catdog.mapper.UserMapper;
import kr.co.catdog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImp implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    ModelMapper modelMapper;

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
        int result = userMapper.update(modelMapper.map(userDTO, UserVO.class));
        return !(result > 0) ? 0 : 1;
    }

    @Override
    public int delete(String user_id) {
        UserDTO userDTO = findById(user_id);
        int result = userMapper.delete(modelMapper.map(userDTO, UserVO.class));
        return !(result > 0) ? 0 : 1;
    }
}
