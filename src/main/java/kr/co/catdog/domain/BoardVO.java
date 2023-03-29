package kr.co.catdog.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class BoardVO {
    private int id;
    private String writer;
    private String content;

}
