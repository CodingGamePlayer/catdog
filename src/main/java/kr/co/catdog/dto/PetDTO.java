package kr.co.catdog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetDTO {
    private int pet_no;
    private String user_id;
    private int category_no;
    private String pet_name;
    private int pet_age;
    private int pet_animalnum;
}
