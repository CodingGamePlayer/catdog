package kr.co.catdog.service.impl;

import kr.co.catdog.CatdogApplication;
import kr.co.catdog.domain.BoardVO;
import kr.co.catdog.dto.BoardDTO;
import kr.co.catdog.dto.GovermentHospitalDTO;
import kr.co.catdog.dto.PetDTO;
import kr.co.catdog.mapper.BoardMapper;
import kr.co.catdog.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

    private BoardMapper boardMapper;
    private ModelMapper modelMapper;

    @Override
    public List<BoardDTO> getBoardList() {
        return boardMapper.getRecentBoard().stream().map(BoardVO -> modelMapper.map(BoardVO, BoardDTO.class)).collect(Collectors.toList());
    }

    @Override
    public int insert(BoardDTO boardDTO) {
        BoardVO boardVo = modelMapper.map(boardDTO, BoardVO.class);
        return boardMapper.insert(boardVo);
    }

    @Override
    public BoardDTO build(Map<String, Object> boardData) {
        String writer = (String)boardData.get("writer");
        String content = (String)boardData.get("content");

        if (writer.isEmpty() || writer == null) {
            writer = "익명";
        }
        return BoardDTO.builder()
                .writer(writer)
                .content(content)
                .build();
    }

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper, ModelMapper modelMapper) {
        this.boardMapper = boardMapper;
        this.modelMapper = modelMapper;
    }
}
