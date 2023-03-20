package kr.co.catdog.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class KakaoSearchResponse {

    public Meta meta;
    public ArrayList<Document> documents;

    @Data
    public class Document{

        public int id;
        public String address_name;
        public String phone;
        public String place_name;
        public String place_url;
        public String road_address_name;
        public double x;
        public double y;
    }

    public class Meta{
        public boolean is_end;
        public int pageable_count;
        public int total_count;
        ArrayList<SameName> sameNames;

    }

    public class SameName {
        public String keyword;
        public String[] region;
        public String selected_region;
    }
}
