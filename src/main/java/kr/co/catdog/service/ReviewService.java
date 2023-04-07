package kr.co.catdog.service;

import kr.co.catdog.dto.PageDTO;
import kr.co.catdog.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> selectAll(PageDTO pageDTO);

    int insert(ReviewDTO reviewDTO);

    int delete(int review_no);
}
