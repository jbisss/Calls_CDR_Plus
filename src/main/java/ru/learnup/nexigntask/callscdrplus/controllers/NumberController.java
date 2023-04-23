package ru.learnup.nexigntask.callscdrplus.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.server.ResponseStatusException;
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
import ru.learnup.nexigntask.callscdrplus.dto.getnumberdetails.Subscriber;
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

    /**
     * Возвращает информацию об абоненте по номеру телефона
     *
     * @param number номер телефона
     * @return информацию об абоненте
     */
    @GetMapping("/abonent/report/{number}")
    public Subscriber getNumberDetails(@PathVariable String number) {
        Subscriber sub = subscriberCache.getSubscribers().get(number);
        Client client = clientService.getClientByPhoneNumber(number);
        if(client == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Abonent with a such number is not found!");
        }
        if (sub == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no calls for this abonent!");
        }
        return sub;
    }

    /**
     * Добавляет нового абонента в базу данных
     *
     * @param newAbonentRequestResponseDto потенциально новый абонент
     * @return информация о добавленном абоненте
     */
    @PostMapping("/manager/abonent")
    public NewAbonentRequestResponseDto newAbonent(@RequestBody NewAbonentRequestResponseDto newAbonentRequestResponseDto) {
        String number = newAbonentRequestResponseDto.getNumberPhone();
        Client client = clientService.getClientByPhoneNumber(number);
        if (client != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Abonent is already exist!");
        }
        // получаем максимальный id, чтобы выбрать новый id для нового абонента
        long newId = clientService.getMaxId() + 1;
        String newNumber = newAbonentRequestResponseDto.getNumberPhone();
        double newBalance = newAbonentRequestResponseDto.getBalance();
        Tariff newTariff = tariffService.getTariffById(newAbonentRequestResponseDto.getTariffId());
        int newBenefitMinutesLeft = newTariff.getBenefitMinutes();
        Client newClient = new Client(newId, newNumber, newBalance, newBenefitMinutesLeft, newTariff);
        clientService.saveClient(newClient);
        return newAbonentRequestResponseDto;
    }

    /**
     * Смена тарифа для абонента с указанным в request номере
     *
     * @param changeTariffRequestDto данные о смене тарифа для определённого номера
     * @return информация об успешной смене тарифа
     */
    @PatchMapping("/manager/changeTariff")
    public ChangeTariffResponseDto changeTariff(@RequestBody ChangeTariffRequestDto changeTariffRequestDto) {
        Client client = clientService.getClientByPhoneNumber(changeTariffRequestDto.getPhoneNumber());
        Tariff tariff = tariffService.getTariffById(changeTariffRequestDto.getTariffId());
        if (client == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Abonent with a such number doesn't exist!");
        }
        if (client.getTariff().getTariffId().equals(tariff.getTariffId())) {
            throw new ResponseStatusException(HttpStatus.MULTI_STATUS, "Abonent already has this tariff!");
        }
        client.setBenefitMinutesLeft(tariff.getBenefitMinutes());
        client.setTariff(tariff);
        clientService.saveClient(client);
        return new ChangeTariffResponseDto(client.getId(), client.getPhoneNumber(), client.getTariff().getTariffId());
    }

    /**
     * Пополнение баланса для абонента с указанным номером
     *
     * @param addBalanceRequestDto информация о сумме для пополнения баланса
     * @return ответ об удачной смене баланса с информацией о новой сумме
     * содержащейся на балансе
     */
    @PatchMapping("/abonent/pay")
    public AddBalanceResponseDto addBalance(@RequestBody AddBalanceRequestDto addBalanceRequestDto) {
        String number = addBalanceRequestDto.getPhoneNumber();
        Client client = clientService.getClientByPhoneNumber(number);
        if (client == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Abonent is not found!");
        }
        client.setBalance(client.getBalance() + addBalanceRequestDto.getMoney());
        clientService.saveClient(client);
        return new AddBalanceResponseDto(client.getId(), client.getPhoneNumber(), client.getBalance());
    }

    /**
     * Получаем запрос с требованием произвести тарификацию
     *
     * @param chargeRequestDto действие с требованием начать тарификацию
     * @return протарифицированные номера
     */
    @PatchMapping("/manager/billing")
    public ChargeResponseDto makeBilling(@RequestBody ChargeRequestDto chargeRequestDto) {
        if (!chargeRequestDto.getAction().equals("run")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid action request!");
        }
        billingService.startBilling();
        List<ChargeNumberBalanceDto> chargeNumberBalanceDtoList = new ArrayList<>();
        for (Client client : subscriberCache.getCachedClients()) {
            chargeNumberBalanceDtoList.add(new ChargeNumberBalanceDto(client.getPhoneNumber(), client.getBalance()));
        }
        return new ChargeResponseDto(chargeNumberBalanceDtoList);
    }
}
