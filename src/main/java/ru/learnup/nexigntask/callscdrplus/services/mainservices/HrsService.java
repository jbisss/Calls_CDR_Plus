package ru.learnup.nexigntask.callscdrplus.services.mainservices;

import org.springframework.stereotype.Service;
import ru.learnup.nexigntask.callscdrplus.cache.SubscriberCache;
import ru.learnup.nexigntask.callscdrplus.entities.Client;
import ru.learnup.nexigntask.callscdrplus.parsers.CdrPlusParser;
import ru.learnup.nexigntask.callscdrplus.entities.Tariff;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Service
public class HrsService {

    private final CdrPlusParser cdrPlusParser;
    private final SubscriberCache subscriberCache;

    public HrsService(CdrPlusParser cdrPlusParser, SubscriberCache subscriberCache) {
        this.cdrPlusParser = cdrPlusParser;
        this.subscriberCache = subscriberCache;
    }

    /**
     * Executes HRS Service.
     * Итогом работы данного сервиса является тарификация абонентов.
     */
    public void execute(){
        try {
            cdrPlusParser.parseFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Map<String, Tariff> numberTariff = subscriberCache.getCachedNumberTariff();
        Set<Client> clients = subscriberCache.getCachedClients();

        for(Client client : clients) {
            if(numberTariff.containsKey(client.getPhoneNumber())
                    && subscriberCache.getSubscribers().containsKey(client.getPhoneNumber())) {
                subscriberCache.getSubscribers().get(client.getPhoneNumber()).countCallsCost(client);
            }
        }
    }
}
