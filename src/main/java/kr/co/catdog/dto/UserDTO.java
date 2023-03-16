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
    String user_id;
    String user_pw;
    String user_name;
    String user_phoneNumber;
    LocalDate user_regdate;
    String user_image;
    Boolean user_matchinguse;

}
