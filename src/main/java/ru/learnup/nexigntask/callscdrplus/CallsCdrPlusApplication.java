package ru.learnup.nexigntask.callscdrplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.learnup.nexigntask.callscdrplus.services.mainservices.BillingService;
import ru.learnup.nexigntask.callscdrplus.services.mainservices.CacheService;

@SpringBootApplication
public class CallsCdrPlusApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(CallsCdrPlusApplication.class, args);
        // Cache data from database
        context.getBean(CacheService.class).cacheDataSource();
        // Start billing
        context.getBean(BillingService.class).startBilling();
    }
}
