package ru.cource.springTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.cource.springTask")
public class Main {
    public static void main(String[] args){
        SpringApplication.run(Main.class,args);
    }
}