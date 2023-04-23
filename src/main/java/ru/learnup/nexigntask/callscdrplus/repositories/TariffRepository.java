package ru.learnup.nexigntask.callscdrplus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.learnup.nexigntask.callscdrplus.entities.Tariff;

public interface TariffRepository extends JpaRepository<Tariff, String> {

    /**
     * Возвращает тариф по его id
     *
     * @param tariffId id тарифа
     * @return тариф
     */
    Tariff findTariffByTariffId(String tariffId);
}
