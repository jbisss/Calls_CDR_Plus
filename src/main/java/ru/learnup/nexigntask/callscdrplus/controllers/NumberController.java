package ru.learnup.nexigntask.callscdrplus.controllers;

import org.springframework.web.bind.annotation.*;
import ru.learnup.nexigntask.callscdrplus.cache.SubscriberCache;
import ru.learnup.nexigntask.callscdrplus.dto.addbalance.AddBalanceRequestDto;
import ru.learnup.nexigntask.callscdrplus.dto.addbalance.AddBalanceResponseDto;
import ru.learnup.nexigntask.callscdrplus.dto.changetariff.ChangeTariffRequestDto;
import ru.learnup.nexigntask.callscdrplus.dto.changetariff.ChangeTariffResponseDto;
import ru.learnup.nexigntask.callscdrplus.dto.charge.ChargeNumberBalanceDto;
import ru.learnup.nexigntask.callscdrplus.dto.charge.ChargeRequestDto;
import ru.learnup.nexigntask.callscdrplus.dto.charge.ChargeResponseDto;
import ru.learnup.nexigntask.callscdrplus.dto.newabonent.NewAbonentRequestResponseDto;
import ru.learnup.nexigntask.callscdrplus.entity.Client;
import ru.learnup.nexigntask.callscdrplus.entity.Tariff;
import ru.learnup.nexigntask.callscdrplus.pojo.callresults.Subscriber;
import ru.learnup.nexigntask.callscdrplus.repository.RomashkaRepository;
import ru.learnup.nexigntask.callscdrplus.repository.TariffRepository;
import ru.learnup.nexigntask.callscdrplus.service.BillingService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NumberController {

    private final SubscriberCache subscriberCache;
    private final RomashkaRepository romashkaRepository;
    private final TariffRepository tariffRepository;
    private final BillingService billingService;

    public NumberController(SubscriberCache subscriberCache, RomashkaRepository romashkaRepository, TariffRepository tariffRepository, BillingService billingService) {
        this.subscriberCache = subscriberCache;
        this.romashkaRepository = romashkaRepository;
        this.tariffRepository = tariffRepository;
        this.billingService = billingService;
    }

    @GetMapping("/abonent/report/{number}")
    public Subscriber getNumberDetail(@PathVariable String number) {
        return subscriberCache.getSubscribers().get(number);
    }

    @PostMapping("/manager/abonent")
    public NewAbonentRequestResponseDto newAbonent(@RequestBody NewAbonentRequestResponseDto newAbonentRequestResponseDto) {
        // System.out.println(newAbonentRequestResponseDto);
        String number = newAbonentRequestResponseDto.getNumberPhone();
        // System.out.println(number);
        Client client = romashkaRepository.findClientByPhoneNumber(number);
        // System.out.println(client);
        if (client == null) {
            long newId = romashkaRepository.findMaxId() + 1;
            String newNumber = newAbonentRequestResponseDto.getNumberPhone();
            double newBalance = newAbonentRequestResponseDto.getBalance();
            Tariff newTariff = tariffRepository.findTariffByTariffId(newAbonentRequestResponseDto.getTariffId());
            int newBenefitMinutesLeft = newTariff.getBenefitMinutes();
            Client newClient = new Client(newId, newNumber, newBalance, newBenefitMinutesLeft, newTariff);
            romashkaRepository.save(newClient);
            return newAbonentRequestResponseDto;
        }
        // добавить выброс ошибки!
        return null;
    }

    @PatchMapping("/manager/changeTariff")
    public ChangeTariffResponseDto changeTariff(@RequestBody ChangeTariffRequestDto changeTariffRequestDto) {
        Client client = romashkaRepository.findClientByPhoneNumber(changeTariffRequestDto.getPhoneNumber());
        Tariff tariff = tariffRepository.findTariffByTariffId(changeTariffRequestDto.getTariffId());
        if (client != null && !client.getTariff().getTariffId().equals(tariff.getTariffId())) {
            client.setBenefitMinutesLeft(tariff.getBenefitMinutes());
            client.setTariff(tariff);
            romashkaRepository.save(client);
            return new ChangeTariffResponseDto(client.getId(), client.getPhoneNumber(), client.getTariff().getTariffId());
        }
        // добавить выброс ошибки!
        return null;
    }

    @PatchMapping("/abonent/pay")
    public AddBalanceResponseDto addBalance(@RequestBody AddBalanceRequestDto addBalanceRequestDto) {
        String number = addBalanceRequestDto.getPhoneNumber();
        Client client = romashkaRepository.findClientByPhoneNumber(number);
        if (client != null) {
            client.setBalance(client.getBalance() + addBalanceRequestDto.getMoney());
            romashkaRepository.save(client);
            return new AddBalanceResponseDto(client.getId(), client.getPhoneNumber(), client.getBalance());
        }
        // добавить выброс ошибки!
        return null;
    }

    @PatchMapping("/manager/billing")
    public ChargeResponseDto makeBilling(@RequestBody ChargeRequestDto chargeRequestDto) {
        if (chargeRequestDto.getAction().equals("run")) {
            billingService.startBilling();
            List<ChargeNumberBalanceDto> chargeNumberBalanceDtoList = new ArrayList<>();
            for (Client client : subscriberCache.getCachedClients()) {
                chargeNumberBalanceDtoList.add(new ChargeNumberBalanceDto(client.getPhoneNumber(), client.getBalance()));
            }
            return new ChargeResponseDto(chargeNumberBalanceDtoList);
        }
        // добавить выброс ошибки!
        return null;
    }
}
