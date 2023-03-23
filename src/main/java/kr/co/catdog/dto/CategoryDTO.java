package kr.co.catdog.dto;

import kr.co.catdog.domain.Category1VO;
import kr.co.catdog.domain.Category2VO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {
    private List<Category1VO> category1VOList;
    private List<Category2VO> category2VOList;
}
