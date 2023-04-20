package ru.learnup.nexigntask.callscdrplus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class FileConfig {

    @Bean
    public File cdrFile(){
        return new File(".\\src\\main\\resources\\static\\cdr.txt");
    }

    @Bean
    public File cdrPlusFile(){
        return new File(".\\src\\main\\resources\\static\\cdrplus.txt");
    }
}
