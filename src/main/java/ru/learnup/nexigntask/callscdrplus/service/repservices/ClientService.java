package ru.learnup.nexigntask.callscdrplus.service.repservices;

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

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Set<String> getNumbers() {
        return repository.findAllNumbers();
    }

    public Set<Client> getClients(){
        return new HashSet<>(repository.findAll());
    }

    public Map<String,Tariff> getPositive(){
        List<NumberTariff> numberTariffs = repository.findPositiveBalance();
        return numberTariffs.stream()
                .collect(Collectors.toMap(NumberTariff::getNumber, NumberTariff::getTariffId));
    }

    public void saveClients(Set<Client> clients){
        repository.saveAll(clients);
    }

    public void saveClient(Client client){
        repository.save(client);
    }

    public Client getClientByPhoneNumber(String phoneNumber){
        return repository.findClientByPhoneNumber(phoneNumber);
    }

    public long getMaxId() {
        return repository.findMaxId();
    }
}
