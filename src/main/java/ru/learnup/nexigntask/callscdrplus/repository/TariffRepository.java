package ru.learnup.nexigntask.callscdrplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.learnup.nexigntask.callscdrplus.entity.Tariff;

public interface TariffRepository extends JpaRepository<Tariff, String> {

    Tariff findTariffByTariffId(String tariffId);
}
