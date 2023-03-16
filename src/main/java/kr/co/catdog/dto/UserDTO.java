package kr.co.catdog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String user_id;
    private String user_pw;
    private String user_name;
    private String user_phoneNumber;
    private LocalDate user_regdate;
    private  String user_image;
    private  Boolean user_matchinguse;

}
