package ru.learnup.nexigntask.callscdrplus.service.mainservices;

import org.springframework.stereotype.Service;
import ru.learnup.nexigntask.callscdrplus.cache.SubscriberCache;
import ru.learnup.nexigntask.callscdrplus.service.repservices.ClientService;

@Service
public class CacheService {

    private final SubscriberCache subscriberCache;
    private final ClientService clientService;

    public CacheService(SubscriberCache subscriberCache, ClientService clientService) {
        this.subscriberCache = subscriberCache;
        this.clientService = clientService;
    }

    /**
     * Сохраняем данные из базы данных в кеш
     */
    public void cacheDataSource(){
        // Getting Clients from DB
        subscriberCache.setCachedClients(clientService.getClients());
        // Getting Number of client with Tariff from DB
        subscriberCache.setCachedNumberTariff(clientService.getPositive());
    }
}
