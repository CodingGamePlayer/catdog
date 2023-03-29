package kr.co.catdog.apicontroller;


import kr.co.catdog.dto.BoardDTO;
import kr.co.catdog.dto.HospitalDTO;
import kr.co.catdog.dto.PetDTO;
import kr.co.catdog.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class BoardApiController {

    private BoardService boardService;

    @PostMapping("/api/user/board/writeContent/{id}")
    public int insertBoard(@PathVariable("id") String id, @RequestBody Map<String, Object> boardData) {
        return boardService.insert(boardService.build(boardData));
    }

    @GetMapping("/api/user/board/loadContent/")
    public List<BoardDTO> boardList() {
        log.info("getBoardData API");
        return boardService.getBoardList();
    }
    @Autowired
    public BoardApiController(BoardService boardService) {
        this.boardService = boardService;
    }
}
