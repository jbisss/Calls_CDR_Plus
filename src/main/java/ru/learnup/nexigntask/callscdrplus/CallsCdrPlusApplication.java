package ru.learnup.nexigntask.callscdrplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.learnup.nexigntask.callscdrplus.cache.SubscriberCache;
import ru.learnup.nexigntask.callscdrplus.service.mainservices.BillingService;
import ru.learnup.nexigntask.callscdrplus.service.repservices.ClientService;

@SpringBootApplication
public class CallsCdrPlusApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(CallsCdrPlusApplication.class, args);
        ClientService clientService = context.getBean(ClientService.class);
        // Getting Clients from DB
        context.getBean(SubscriberCache.class).setCachedClients(clientService.getClients());
        // Getting Number of client with Tariff from DB
        context.getBean(SubscriberCache.class).setCachedNumberTariff(clientService.getPositive());
        // Start billing
        context.getBean(BillingService.class).startBilling();
    }
}
