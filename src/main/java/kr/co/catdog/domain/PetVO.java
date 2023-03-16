package kr.co.catdog.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class PetVO {
    private int pet_no;
    private String user_id;
    private int category_no;
    private String pet_name;
    private int pet_age;
    private int pet_animalnum;
}
