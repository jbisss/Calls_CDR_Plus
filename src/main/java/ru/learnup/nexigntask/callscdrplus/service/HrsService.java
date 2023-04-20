package ru.learnup.nexigntask.callscdrplus.service;

import org.springframework.stereotype.Service;
import ru.learnup.nexigntask.callscdrplus.entity.Client;
import ru.learnup.nexigntask.callscdrplus.parsers.CdrPlusParser;
import ru.learnup.nexigntask.callscdrplus.pojo.callresults.Subscriber;
import ru.learnup.nexigntask.callscdrplus.entity.Tariff;
import ru.learnup.nexigntask.callscdrplus.servicedb.RomashkaService;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Service
public class HrsService {

    private final RomashkaService romashkaService;
    private final CdrPlusParser cdrPlusParser;

    public HrsService(RomashkaService romashkaService, CdrPlusParser cdrPlusParser) {
        this.romashkaService = romashkaService;
        this.cdrPlusParser = cdrPlusParser;
    }

    /**
     * Executes HRS Service.
     * Итогом работы данного сервиса является тарификация абонентов.
     */
    public void execute(){
        File cdrPlus = new File(".\\src\\main\\resources\\static\\cdrplus.txt");
        try {
            cdrPlusParser.parseFile(cdrPlus);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Map<String, Tariff> numberTariff = romashkaService.getPositive();
        Set<Client> clients = romashkaService.getClients();

        for(Client client : clients) {
            if(numberTariff.containsKey(client.getPhoneNumber())
                    && Subscriber.subscribers.containsKey(client.getPhoneNumber())) {
                Subscriber.subscribers.get(client.getPhoneNumber()).countCallsCost(client);
            }
        }
        printSubscribersTotalCost();
    }

    private void printSubscribersTotalCost(){
        for (String key : Subscriber.subscribers.keySet()) {
            System.out.println(Subscriber.subscribers.get(key));
        }
    }
}
