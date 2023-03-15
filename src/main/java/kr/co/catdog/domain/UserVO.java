package kr.co.catdog.domain;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder

public class UserVO {
    String user_id;
    String user_pw;
    String user_name;
    String user_phoneNumber;
    LocalDate user_regdate;
    String user_image;
    Boolean user_matchinguse;
}
