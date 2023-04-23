package ru.learnup.nexigntask.callscdrplus.services.mainservices;

import org.springframework.stereotype.Service;
import ru.learnup.nexigntask.callscdrplus.cache.SubscriberCache;
import ru.learnup.nexigntask.callscdrplus.parsers.CdrParser;
import ru.learnup.nexigntask.callscdrplus.services.repservices.ClientService;

import java.io.IOException;

@Service
public class BrtService {

    private final CdrParser cdrParser;
    private final ClientService clientService;
    private final SubscriberCache subscriberCache;

    public BrtService(CdrParser cdrParser, ClientService clientService, SubscriberCache subscriberCache) {
        this.cdrParser = cdrParser;
        this.clientService = clientService;
        this.subscriberCache = subscriberCache;
    }

    /**
     * Executes BRT Service.
     * Итогом работы данного сервиса является сгенерированный CDR+ файл.
     */
    public void execute() {
        try {
            cdrParser.parseFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Заносим из сформированного кеша данные о всех клиентах в базу данных
     */
    public void updateDatabase(){
        clientService.saveClients(subscriberCache.getCachedClients());
    }
}
