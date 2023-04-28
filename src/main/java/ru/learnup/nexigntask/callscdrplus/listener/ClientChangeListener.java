package ru.learnup.nexigntask.callscdrplus.listener;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.learnup.nexigntask.callscdrplus.entity.Client;

@Component
@Slf4j
public class ClientChangeListener {

    /**
     * Метод, срабатывающий при внесении изменений клиенту из базы данных
     *
     * @param client клиент с измененниями
     */
    @PostUpdate
    public void afterUpdate(Client client) {
        log.info("Client updated!");
    }

    /**
     * Метод, срабатывающий при добавлении нового клиента в базу данных
     *
     * @param client добавленный клиент
     */
    @PostPersist
    public void afterPersist(Client client) {
        log.info("Client added!");
    }
}
