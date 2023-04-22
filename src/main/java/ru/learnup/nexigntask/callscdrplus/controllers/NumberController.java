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
import ru.learnup.nexigntask.callscdrplus.service.mainservices.BillingService;
import ru.learnup.nexigntask.callscdrplus.service.repservices.ClientService;
import ru.learnup.nexigntask.callscdrplus.service.repservices.TariffService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NumberController {

    private final SubscriberCache subscriberCache;
    private final ClientService clientService;
    private final TariffService tariffService;
    private final BillingService billingService;

    public NumberController(SubscriberCache subscriberCache, ClientService clientService, TariffService tariffService, BillingService billingService) {
        this.subscriberCache = subscriberCache;
        this.clientService = clientService;
        this.tariffService = tariffService;
        this.billingService = billingService;
    }

    @GetMapping("/abonent/report/{number}")
    public Subscriber getNumberDetail(@PathVariable String number) {
        return subscriberCache.getSubscribers().get(number);
    }

    @PostMapping("/manager/abonent")
    public NewAbonentRequestResponseDto newAbonent(@RequestBody NewAbonentRequestResponseDto newAbonentRequestResponseDto) {
        String number = newAbonentRequestResponseDto.getNumberPhone();
        Client client = clientService.getClientByPhoneNumber(number);
        if (client == null) {
            long newId = clientService.getMaxId() + 1;
            String newNumber = newAbonentRequestResponseDto.getNumberPhone();
            double newBalance = newAbonentRequestResponseDto.getBalance();
            Tariff newTariff = tariffService.getTariffById(newAbonentRequestResponseDto.getTariffId());
            int newBenefitMinutesLeft = newTariff.getBenefitMinutes();
            Client newClient = new Client(newId, newNumber, newBalance, newBenefitMinutesLeft, newTariff);
            clientService.saveClient(newClient);
            return newAbonentRequestResponseDto;
        }
        // добавить выброс ошибки!
        return null;
    }

    @PatchMapping("/manager/changeTariff")
    public ChangeTariffResponseDto changeTariff(@RequestBody ChangeTariffRequestDto changeTariffRequestDto) {
        Client client = clientService.getClientByPhoneNumber(changeTariffRequestDto.getPhoneNumber());
        Tariff tariff = tariffService.getTariffById(changeTariffRequestDto.getTariffId());
        if (client != null && !client.getTariff().getTariffId().equals(tariff.getTariffId())) {
            client.setBenefitMinutesLeft(tariff.getBenefitMinutes());
            client.setTariff(tariff);
            clientService.saveClient(client);
            return new ChangeTariffResponseDto(client.getId(), client.getPhoneNumber(), client.getTariff().getTariffId());
        }
        // добавить выброс ошибки!
        return null;
    }

    @PatchMapping("/abonent/pay")
    public AddBalanceResponseDto addBalance(@RequestBody AddBalanceRequestDto addBalanceRequestDto) {
        String number = addBalanceRequestDto.getPhoneNumber();
        Client client = clientService.getClientByPhoneNumber(number);
        if (client != null) {
            client.setBalance(client.getBalance() + addBalanceRequestDto.getMoney());
            clientService.saveClient(client);
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
