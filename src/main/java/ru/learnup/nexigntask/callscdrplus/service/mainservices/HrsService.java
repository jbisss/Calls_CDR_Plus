package ru.learnup.nexigntask.callscdrplus.service.mainservices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.learnup.nexigntask.callscdrplus.cache.SubscriberCache;
import ru.learnup.nexigntask.callscdrplus.dto.getnumberdetails.Subscriber;
import ru.learnup.nexigntask.callscdrplus.entity.Client;
import ru.learnup.nexigntask.callscdrplus.parsers.CdrPlusParser;
import ru.learnup.nexigntask.callscdrplus.entity.Tariff;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Slf4j
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
    public void execute() {
        try {
            cdrPlusParser.parseFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Map<String, Tariff> numberTariff = subscriberCache.getCachedNumberTariff();
        Set<Client> clients = subscriberCache.getCachedClients();
        Map<String, Subscriber> subs = subscriberCache.getSubscribers();

        for (Client client : clients) {
            // тарифицируем абонентов, только с положительным балансом
            // так как в numberTariff - информация о тарифах абонентов только с положительным балансом
            if (numberTariff.containsKey(client.getPhoneNumber()) && subs.containsKey(client.getPhoneNumber())) {
                subs.get(client.getPhoneNumber()).countCallsCost(client);
            }
        }

        printSubscribersNumbers(subs);
    }

    public void printSubscribersNumbers(Map<String, Subscriber> subs) {
        subs.forEach((number, sub) -> log.info(number));
    }
}
