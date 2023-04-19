package ru.learnup.nexigntask.callscdrplus.service;

import org.springframework.stereotype.Service;

@Service
public class HrsService {

    private final RomashkaService romashkaService;

    public HrsService(RomashkaService romashkaService) {
        this.romashkaService = romashkaService;
    }

    public void execute(){

    }
}
