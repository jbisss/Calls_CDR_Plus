package ru.learnup.nexigntask.callscdrplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.learnup.nexigntask.callscdrplus.entity.Client;
import ru.learnup.nexigntask.callscdrplus.pojo.dbresults.NumberTariff;

import java.util.List;
import java.util.Set;

public interface RomashkaRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c.phoneNumber FROM Client c")
    Set<String> findAllNumbers();

    List<Client> findAll();

    @Query("select NEW ru.learnup.nexigntask.callscdrplus.pojo.dbresults.NumberTariff(" +
            " c.phoneNumber, c.tariff) from Client as c where c.balance > 0")
    List<NumberTariff> findPositiveBalance();

}
