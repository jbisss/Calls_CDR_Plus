package ru.learnup.nexigntask.callscdrplus.listener;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.learnup.nexigntask.callscdrplus.cache.SubscriberCache;
import ru.learnup.nexigntask.callscdrplus.entity.Client;

@Component
@Slf4j
public class ClientChangeListener {

    private SubscriberCache subscriberCache;

    @Autowired
    public void setSubscriberCache(SubscriberCache subscriberCache) {
        this.subscriberCache = subscriberCache;
    }

    /**
     * Метод, срабатывающий при внесении изменений клиенту из базы данных
     *
     * @param client клиент с измененниями
     */
    @PostUpdate
    public void afterUpdate(Client client) {
        for (Client currentClient : subscriberCache.getCachedClients()) {
            if (currentClient.getPhoneNumber().equals(client.getPhoneNumber())) {
                currentClient.copyClientFields(client);
                break;
            }
        }
        subscriberCache.getSubscribers().get(client.getPhoneNumber()).setTariff(client.getTariff().getTariffId());
        log.info("Client with id = {} updated!", client.getId());
    }

    /**
     * Метод, срабатывающий при добавлении нового клиента в базу данных
     *
     * @param client добавленный клиент
     */
    @PostPersist
    public void afterPersist(Client client) {
        String clientNumber = client.getPhoneNumber();
        subscriberCache.getCachedClients().add(client);
        subscriberCache.getCachedNumberTariff().put(clientNumber, client.getTariff());
        log.info("Client with id = {} added!", client.getId());
    }
}
