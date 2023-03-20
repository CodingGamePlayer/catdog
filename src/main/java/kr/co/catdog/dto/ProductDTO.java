package kr.co.catdog.dto;

import kr.co.catdog.domain.Category1VO;
import kr.co.catdog.domain.MediaVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private int product_no;
    private int category1_no;
    private List<Category1VO> category1VOList;
    private String product_name;
    private int product_stock;
    private String product_image;
    private List<MediaVO> mediaVOList;
    private MultipartFile file;
    private String product_content;
    private int product_price;
    private LocalDate product_regdate;
}
