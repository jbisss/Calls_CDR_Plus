package ru.learnup.nexigntask.callscdrplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.learnup.nexigntask.callscdrplus.cache.SubscriberCache;
import ru.learnup.nexigntask.callscdrplus.service.*;

@SpringBootApplication
public class CallsCdrPlusApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(CallsCdrPlusApplication.class, args);
        RomashkaService romashkaService = context.getBean(RomashkaService.class);
        // Getting Clients from DB
        context.getBean(SubscriberCache.class).setCachedClients(romashkaService.getClients());
        // Getting Number of client with Tariff from DB
        context.getBean(SubscriberCache.class).setCachedNumberTariff(romashkaService.getPositive());
        // Start billing
        context.getBean(BillingService.class).startBilling();
    }
}
