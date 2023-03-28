package kr.co.catdog.service.impl;

import kr.co.catdog.dto.ProductDTO;
import kr.co.catdog.mapper.ReviewMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
@Slf4j
class ShopServiceImpTest {
    @Autowired
    ShopServiceImp shopServiceImp;
    @Autowired
    ReviewMapper reviewMapper;

    @Test
    void orderByReviewCount() {

//        List<Map<String, Object>> list = new ArrayList<>();
//        shopServiceImp.selectAll().forEach(productDTO -> {
//            Map<String, Object> map = new HashMap<>();
//            map.put("DTO", productDTO);
//            map.put("count", reviewMapper.selectAll(productDTO.getProduct_no()).size());
//            list.add(map);
//        });
//
//        list.sort(
//            Comparator.comparing((Map<String, Object> map) -> (Integer) map.get("count")).reversed()
//        );
//        log.info("a" + String.valueOf(list));
//        list.forEach(stringObjectMap ->);
    }
}