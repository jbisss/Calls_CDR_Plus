package ru.learnup.nexigntask.callscdrplus.service.repservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.learnup.nexigntask.callscdrplus.entity.Client;
import ru.learnup.nexigntask.callscdrplus.entity.Tariff;
import ru.learnup.nexigntask.callscdrplus.dao.NumberTariff;
import ru.learnup.nexigntask.callscdrplus.repository.ClientRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    @Qualifier("clientRepositoryJpa")
    private ClientRepository clientRepository;

    /**
     * Получаем множество номеров
     *
     * @return множество номеров телефонов
     */
    public Set<String> getNumbers() {
        return clientRepository.findAllNumbers();
    }

    /**
     * Получаем множество клиентов из базы данных
     *
     * @return множетсво клиентов
     */
    public Set<Client> getClients(){
        return new HashSet<>(clientRepository.findAll());
    }

    /**
     * Получаем информацию только о тех пользователях, чей баланс положителен
     *
     * @return множество пар номер телефона-тариф
     */
    public Map<String,Tariff> getPositive(){
        List<NumberTariff> numberTariffs = clientRepository.findPositiveBalance();
        return numberTariffs.stream()
                .collect(Collectors.toMap(NumberTariff::getNumber, NumberTariff::getTariffId));
    }

    /**
     * Сохраняем клиентов в базу данных
     *
     * @param clients множество клиентов
     */
    public void saveClients(Set<Client> clients){
        clientRepository.saveAll(clients);
    }

    /**
     * Сохряняем клиента в базу данных
     *
     * @param client клиент
     */
    public void saveClient(Client client){
        clientRepository.save(client);
    }

    /**
     * Получаем информацию о клиенте из базы данных по номеру телефона
     *
     * @param phoneNumber номер телефона
     * @return клиент
     */
    public Client getClientByPhoneNumber(String phoneNumber){
        return clientRepository.findClientByPhoneNumber(phoneNumber);
    }

    /**
     * Получаем максимальный id клиента
     *
     * @return long maxId
     */
    public long getMaxId() {
        return clientRepository.findMaxId();
    }
}
