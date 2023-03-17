package kr.co.catdog.domain;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder

public class UserVO {
    private String user_id;
    private String user_pw;
    private String user_name;
    private String user_phoneNumber;
    private LocalDate user_regdate;
    private String user_image;
    private Boolean user_matchinguse;

}
