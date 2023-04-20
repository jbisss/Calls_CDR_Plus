package ru.learnup.nexigntask.callscdrplus.service;

import org.springframework.stereotype.Service;
import ru.learnup.nexigntask.callscdrplus.parsers.CdrParser;

import java.io.File;
import java.io.IOException;

@Service
public class BrtService {

    private final CdrParser cdrParser;

    public BrtService(CdrParser cdrParser) {
        this.cdrParser = cdrParser;
    }

    /**
     * Executes BRT Service.
     * Итогом работы данного сервиса является сгенерированный CDR+ файл.
     */
    public void execute() {
        File cdr = new File(".\\src\\main\\resources\\static\\cdr.txt");
        try {
            cdrParser.parseFile(cdr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateDatabase(){

    }
}
