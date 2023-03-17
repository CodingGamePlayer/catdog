package kr.co.catdog.dto;

import kr.co.catdog.domain.Category1VO;
import kr.co.catdog.domain.Category2VO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetDTO {
    private int pet_no;
    private String user_id;
    private List<Category1VO> category1VOList;

    private List<Category2VO> category2VOList;
    private String pet_name;
    private int pet_age;
    private long pet_animalnum;
    private int pet_gender;
}
