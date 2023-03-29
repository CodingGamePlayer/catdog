package kr.co.catdog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UploadResultDTO {
    private String uuid;
    private String fileName;

    public String getLink() {
        return uuid + "_" + fileName;
    }
}
