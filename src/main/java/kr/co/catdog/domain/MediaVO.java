package kr.co.catdog.domain;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class MediaVO {
    private int media_no;
    private int product_no;
    private String media_path;

}
