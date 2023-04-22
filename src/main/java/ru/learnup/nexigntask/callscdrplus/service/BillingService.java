package ru.learnup.nexigntask.callscdrplus.service;

import org.springframework.stereotype.Service;

@Service
public class BillingService {

    private final CdrService cdrService;
    private final BrtService brtService;
    private final HrsService hrsService;

    public BillingService(CdrService cdrService, BrtService brtService, HrsService hrsService) {
        this.cdrService = cdrService;
        this.brtService = brtService;
        this.hrsService = hrsService;
    }

    public void startBilling(){
        // Executing CDR Service
        cdrService.execute();
        // Executing BRT Service
        brtService.execute();
        // Executing HRS Service
        hrsService.execute();
        // Updating DB with BRT Service
        brtService.updateDatabase();
    }
}
