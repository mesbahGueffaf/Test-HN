package fr.hn.hntest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
public class HntestApplication {

    public static void main(String[] args) {
        SpringApplication.run(HntestApplication.class, args);
    }


}
