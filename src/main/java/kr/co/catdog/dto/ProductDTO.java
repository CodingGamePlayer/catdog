package kr.co.catdog.dto;

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
    private String user_id;
    private int category1_no;
    private String product_name;
    private int product_stock;
    private String product_content;
    private int product_price;
    private LocalDate product_regdate;

    private String media_path;
    private List<MultipartFile> files;
    private List<MediaVO> mediaVOList;
}
