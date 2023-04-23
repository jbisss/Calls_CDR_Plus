package ru.learnup.nexigntask.callscdrplus.services.repservices;

import org.springframework.stereotype.Service;
import ru.learnup.nexigntask.callscdrplus.entities.Tariff;
import ru.learnup.nexigntask.callscdrplus.repositories.TariffRepository;

@Service
public class TariffService {

    private final TariffRepository tariffRepository;

    public TariffService(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

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
