package kr.co.catdog.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Data
public class KaKaoMapResponse {
    public Meta meta;
    public ArrayList<Document> documents;

    @Data
    public class Document{
        public String region_type;
        public String code;
        public String address_name;
        public String region_1depth_name;
        public String region_2depth_name;
        public String region_3depth_name;
        public String region_4depth_name;
        public double x;
        public double y;
    }

    public class Meta{
        public int total_count;
    }
}