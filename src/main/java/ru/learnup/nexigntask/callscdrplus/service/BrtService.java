package ru.learnup.nexigntask.callscdrplus.service;

import org.springframework.stereotype.Service;

@Service
public class BrtService {

    private final RomashkaService romashkaService;

    public BrtService(RomashkaService romashkaService) {
        this.romashkaService = romashkaService;
    }

    /**
     * Executes BRT Service.
     * Итогом работы данного сервиса является сгенерированный CDR+ файл.
     */
    public void execute(){

    }
}
