package ru.learnup.nexigntask.callscdrplus.service.repservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.learnup.nexigntask.callscdrplus.entity.Tariff;
import ru.learnup.nexigntask.callscdrplus.repository.TariffRepository;

@Service
public class TariffService {

    @Autowired
    @Qualifier("tariffRepositoryJpa")
    private TariffRepository tariffRepository;

    /**
     * Получаем тариф по его id
     *
     * @param tariffId id тарифа
     * @return тариф
     */
    public Tariff getTariffById(String tariffId){
        return tariffRepository.findTariffByTariffId(tariffId);
    }
}
