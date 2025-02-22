package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class VisualiseApplication {
    public static void main(String[] args) {
        SpringApplication.run(VisualiseApplication.class, args);
    }
    @RequestMapping("/")
    String home(){
        return "hello world";
    }
}
