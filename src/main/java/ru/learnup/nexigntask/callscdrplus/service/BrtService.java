package ru.learnup.nexigntask.callscdrplus.service;

import org.springframework.stereotype.Service;
import ru.learnup.nexigntask.callscdrplus.cache.SubscriberCache;
import ru.learnup.nexigntask.callscdrplus.parsers.CdrParser;
import ru.learnup.nexigntask.callscdrplus.servicedb.RomashkaService;

import java.io.File;
import java.io.IOException;

@Service
public class BrtService {

    private final CdrParser cdrParser;
    private final RomashkaService romashkaService;
    private final SubscriberCache subscriberCache;

    public BrtService(CdrParser cdrParser, RomashkaService romashkaService, SubscriberCache subscriberCache) {
        this.cdrParser = cdrParser;
        this.romashkaService = romashkaService;
        this.subscriberCache = subscriberCache;
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
        romashkaService.saveClients(subscriberCache.getCachedClients());
    }
}
