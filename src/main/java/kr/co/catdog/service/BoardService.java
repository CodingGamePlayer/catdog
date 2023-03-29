package kr.co.catdog.service;

import kr.co.catdog.dto.BoardDTO;

import java.util.List;
import java.util.Map;

public interface BoardService {

    List<BoardDTO> getBoardList();
    int insert(BoardDTO boardDTO);

    BoardDTO build(Map<String, Object> boardData);
}
