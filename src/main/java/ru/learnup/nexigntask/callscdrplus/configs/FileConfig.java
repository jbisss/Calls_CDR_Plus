package ru.learnup.nexigntask.callscdrplus.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class FileConfig {

    /**
     * @return cdr файл, для хранения сгенерированных данных
     * (тип вызова, номер телефона, дата начала звонка, дата конца звонка)
     */
    @Bean
    public File cdrFile(){
        return new File(".\\src\\main\\resources\\static\\cdr.txt");
    }

    /**
     * @return расширенных cdr файл (cdrPlus)
     * дополнительно хранит информацию о тарифе абонента
     */
    @Bean
    public File cdrPlusFile(){
        return new File(".\\src\\main\\resources\\static\\cdrplus.txt");
    }
}
