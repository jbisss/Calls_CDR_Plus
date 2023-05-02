package ru.learnup.nexigntask.callscdrplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.learnup.nexigntask.callscdrplus.entity.Client;
import ru.learnup.nexigntask.callscdrplus.dao.NumberTariff;

import java.util.List;
import java.util.Set;

@Repository("clientRepositoryJpa")
public interface ClientRepository extends JpaRepository<Client, Long> {

    /**
     * Возвращает номера всех клиентов из таблицы клиентов
     *
     * @return номера клиентов
     */
    @Query("SELECT c.phoneNumber FROM Client c")
    Set<String> findAllNumbers();

    /**
     * Возвращает клиента по его номеру
     *
     * @param phoneNumber номер клиента
     * @return клиент
     */
    Client findClientByPhoneNumber(String phoneNumber);

    /**
     * Возвращает всех клиентов из базы данных
     *
     * @return клиентов
     */
    List<Client> findAll();

    /**
     * Возвращает максимальный id из таблицы с клиентами
     *
     * @return максимальный id клиентов
     */
    @Query("SELECT MAX(c.id) FROM Client c")
    long findMaxId();

    /**
     * Возвращает данные из базы данных в формате номера с тарифом
     *
     * @return номер с тарифом
     */
    @Query("select NEW ru.learnup.nexigntask.callscdrplus.dao.NumberTariff(" +
            " c.phoneNumber, c.tariff) from Client as c where c.balance > 0")
    List<NumberTariff> findPositiveBalance();

    /**
     * Сохраняет лист клиентов в базу данных
     *
     * @param entities лист клиентов
     * @return лист клиентов
     * @param <S> наследник клиента
     */
    @Override
    <S extends Client> List<S> saveAll(Iterable<S> entities);

    /**
     * Сохраняет клиента в базу данных
     *
     * @param entity клиент
     * @return клиента
     * @param <S> наследник Client
     */
    @Override
    <S extends Client> S save(S entity);
}
