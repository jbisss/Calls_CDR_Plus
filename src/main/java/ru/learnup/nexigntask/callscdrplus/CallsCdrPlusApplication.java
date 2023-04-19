package ru.learnup.nexigntask.callscdrplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.learnup.nexigntask.callscdrplus.service.BrtService;
import ru.learnup.nexigntask.callscdrplus.service.CdrService;
import ru.learnup.nexigntask.callscdrplus.service.HrsService;
import ru.learnup.nexigntask.callscdrplus.service.RomashkaService;

@SpringBootApplication
public class CallsCdrPlusApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(CallsCdrPlusApplication.class, args);

        // Executing CDR Service
        context.getBean(CdrService.class).execute();
        // Executing BRT Service
        context.getBean(BrtService.class).execute();
        // Executing HRS Service
        context.getBean(HrsService.class).execute();

        RomashkaService romashkaService = context.getBean(RomashkaService.class);

        System.out.println(romashkaService.getPositive());
    }
}
