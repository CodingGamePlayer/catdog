package kr.co.catdog.service.impl;

import kr.co.catdog.dto.CategoryDTO;
import kr.co.catdog.mapper.CategoryMapper;
import kr.co.catdog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDTO selectAll() {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .category1VOList(categoryMapper.selectCategory1())
                .category2VOList(categoryMapper.selectCategory2())
                .build();

        return categoryDTO;
    }

}
