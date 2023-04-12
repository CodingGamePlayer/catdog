package kr.co.catdog.service;

import kr.co.catdog.dto.PageDTO;
import kr.co.catdog.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> selectAll(PageDTO pageDTO);

    List<ReviewDTO> findByUserid(String user_id);

    int insert(ReviewDTO reviewDTO);

    int delete(int review_no);
}
