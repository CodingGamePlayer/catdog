package kr.co.catdog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatdogApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatdogApplication.class, args);
        System.out.println("hello world!");
    }

}
