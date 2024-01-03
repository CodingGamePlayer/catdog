package kr.co.catdog.controller;

import kr.co.catdog.dto.BoardDTO;
import kr.co.catdog.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private BoardService boardService;

    @GetMapping("/")
    String main(@ModelAttribute("successToastMsg") String successToastMsg, @ModelAttribute("warningToastMsg") String warningToastMsg, Model model) {
        List<BoardDTO> boardDTOList = boardService.getBoardList();
        // log.info(boardDTOList.get(0).getContent());
        model.addAttribute("boardDTOList", boardDTOList);
        return "index";
    }

    @Autowired
    public MainController(BoardService boardService) {
        this.boardService = boardService;
    }
}
