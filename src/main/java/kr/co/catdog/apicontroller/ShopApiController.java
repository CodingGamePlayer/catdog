package kr.co.catdog.apicontroller;

import kr.co.catdog.dto.PageDTO;
import kr.co.catdog.dto.ProductDTO;
import kr.co.catdog.dto.ReviewDTO;
import kr.co.catdog.service.ReviewService;
import kr.co.catdog.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ShopApiController {
    private final ShopService shopService;
    private final ReviewService reviewService;
    @PostMapping("/api/user/shop")
    public ResponseEntity<ProductDTO> register(@RequestBody ProductDTO productDTO){

        log.info(String.valueOf(productDTO));
        int result = shopService.insert(productDTO);

        if(result == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PutMapping("/api/user/shop")
    public ResponseEntity<ProductDTO> edit(@RequestBody ProductDTO productDTO) {
        log.info(String.valueOf(productDTO));
        int result = shopService.update(productDTO);

        if(result == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/api/user/shop")
    public List<ProductDTO> list(PageDTO pageDTO) {
        List<ProductDTO> productDTOList = shopService.selectAll(pageDTO);

        return productDTOList;
    }
    @GetMapping("/api/user/shop/detail")
    public List<ReviewDTO> reviewList(PageDTO pageDTO) {
        List<ReviewDTO> reviewDTOList = reviewService.selectAll(pageDTO);

        return reviewDTOList;
    }
}
