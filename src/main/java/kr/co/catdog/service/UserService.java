package kr.co.catdog.service;

import kr.co.catdog.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    int login(UserDTO userDTO, HttpServletRequest request);
    UserDTO findById(String user_id);

    int insert(UserDTO userDTO);

    int update(UserDTO userDTO);

    int delete(String user_id);
}
