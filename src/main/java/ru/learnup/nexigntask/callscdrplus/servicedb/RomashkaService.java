package ru.learnup.nexigntask.callscdrplus.servicedb;

import org.springframework.stereotype.Service;
import ru.learnup.nexigntask.callscdrplus.cache.SubscriberCache;
import ru.learnup.nexigntask.callscdrplus.entity.Client;
import ru.learnup.nexigntask.callscdrplus.entity.Tariff;
import ru.learnup.nexigntask.callscdrplus.pojo.dbresults.NumberTariff;
import ru.learnup.nexigntask.callscdrplus.repository.RomashkaRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RomashkaService {

    private final RomashkaRepository repository;

    public RomashkaService(RomashkaRepository repository) {
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
}
