package ru.learnup.nexigntask.callscdrplus.cache;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.learnup.nexigntask.callscdrplus.entity.Client;
import ru.learnup.nexigntask.callscdrplus.entity.Tariff;
import ru.learnup.nexigntask.callscdrplus.dto.getnumberdetails.Subscriber;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@Getter
@Setter
public class SubscriberCache {

    private Set<Client> cachedClients;
    private Map<String, Tariff> cachedNumberTariff;
    private HashMap<String, Subscriber> subscribers = new HashMap<>();
}
