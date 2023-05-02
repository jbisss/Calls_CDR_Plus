package ru.learnup.nexigntask.callscdrplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.learnup.nexigntask.callscdrplus.entity.Tariff;

@Repository("tariffRepositoryJpa")
public interface TariffRepository extends JpaRepository<Tariff, String> {

    /**
     * Возвращает тариф по его id
     *
     * @param tariffId id тарифа
     * @return тариф
     */
    Tariff findTariffByTariffId(String tariffId);
}
