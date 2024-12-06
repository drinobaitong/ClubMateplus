package org.intership.clubmate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.intership.clubmate.mapper")
public class ClubMateApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClubMateApplication.class, args);
    }

}
