package ru.learnup.nexigntask.callscdrplus.controllers;

import org.springframework.web.bind.annotation.*;
import ru.learnup.nexigntask.callscdrplus.cache.SubscriberCache;
import ru.learnup.nexigntask.callscdrplus.dto.changetariff.ChangeTariffRequestDto;
import ru.learnup.nexigntask.callscdrplus.dto.newabonent.NewAbonentRequestResponseDto;
import ru.learnup.nexigntask.callscdrplus.pojo.callresults.Subscriber;

@RestController
public class NumberController {

    private final SubscriberCache subscriberCache;

    public NumberController(SubscriberCache subscriberCache) {
        this.subscriberCache = subscriberCache;
    }

    @GetMapping("/abonent/report/{number}")
    public Subscriber getNumberDetail(@PathVariable String number){
        return subscriberCache.getSubscribers().get(number);
    }

    @PostMapping("/manager/abonent")
    public NewAbonentRequestResponseDto newAbonent(@RequestBody NewAbonentRequestResponseDto newAbonentRequestResponseDto){
        System.out.println(newAbonentRequestResponseDto);
        return newAbonentRequestResponseDto;
    }

    @PatchMapping("/manager/changeTariff")
    public void changeTariff(@RequestBody ChangeTariffRequestDto changeTariffRequestDto){

    }

    @PatchMapping("/abonent/pay")
    public void addBalance(){

    }

    @PatchMapping("/manager/billing")
    public void makeBilling(){

    }
}
