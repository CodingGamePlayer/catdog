package kr.co.catdog.service;

import kr.co.catdog.dto.UserDTO;

public interface UserService {
    UserDTO findById(String user_id);

    int insert(UserDTO userDTO);

    int update(UserDTO userDTO);

    int delete(String user_id);
}
