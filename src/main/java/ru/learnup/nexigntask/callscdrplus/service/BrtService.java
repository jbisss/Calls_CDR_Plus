package ru.learnup.nexigntask.callscdrplus.service;

import org.springframework.stereotype.Service;
import ru.learnup.nexigntask.callscdrplus.cache.SubscriberCache;
import ru.learnup.nexigntask.callscdrplus.parsers.CdrParser;

import java.io.File;
import java.io.IOException;

@Service
public class BrtService {

    private final CdrParser cdrParser;
    private final RomashkaService romashkaService;
    private final SubscriberCache subscriberCache;
    private final File cdrFile;

    public BrtService(CdrParser cdrParser, RomashkaService romashkaService, SubscriberCache subscriberCache, File cdrFile) {
        this.cdrParser = cdrParser;
        this.romashkaService = romashkaService;
        this.subscriberCache = subscriberCache;
        this.cdrFile = cdrFile;
    }

    /**
     * Executes BRT Service.
     * Итогом работы данного сервиса является сгенерированный CDR+ файл.
     */
    public void execute() {
        try {
            cdrParser.parseFile(cdrFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateDatabase(){
        romashkaService.saveClients(subscriberCache.getCachedClients());
    }
}
