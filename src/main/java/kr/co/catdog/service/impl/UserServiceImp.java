package kr.co.catdog.service.impl;

import kr.co.catdog.domain.UserVO;
import kr.co.catdog.dto.UserDTO;
import kr.co.catdog.mapper.UserMapper;
import kr.co.catdog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserMapper userMapper;
    private final ModelMapper modelMapper;

    @Override
    public UserDTO findById(String user_id) {
        UserDTO userDTO = UserDTO.builder()
                .user_id(user_id).build();
        UserVO uservo = userMapper.findById(userDTO);
        UserDTO dto = modelMapper.map(uservo, UserDTO.class);
        log.info(String.valueOf(dto));
        return dto;

    }

    @Override
    public int insert(UserDTO userDTO) {
        int result = userMapper.insert(userDTO);
        return !(result > 0) ? 0 : 1;
    }

    @Override
    public int update(UserDTO userDTO) {
        int result = userMapper.update(userDTO);
        log.info(String.valueOf(result));

        return !(result > 0) ? 0 : 1;
    }

    @Override
    public int delete(String user_id) {
        UserDTO userDTO = UserDTO.builder()
                .user_id(user_id).build();
        int result = userMapper.delete(userDTO);
        return !(result > 0) ? 0 : 1;
    }
}
