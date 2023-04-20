package ru.learnup.nexigntask.callscdrplus.service;

import org.springframework.stereotype.Service;
import ru.learnup.nexigntask.callscdrplus.parsers.CdrParser;
import ru.learnup.nexigntask.callscdrplus.pojo.callresults.Subscriber;
import ru.learnup.nexigntask.callscdrplus.servicedb.RomashkaService;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class BrtService {

    private final CdrParser cdrParser;
    private final RomashkaService romashkaService;

    public BrtService(CdrParser cdrParser, RomashkaService romashkaService) {
        this.cdrParser = cdrParser;
        this.romashkaService = romashkaService;
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
        // Map<String, Subscriber> subs = Subscriber.subscribers;
    }
}
