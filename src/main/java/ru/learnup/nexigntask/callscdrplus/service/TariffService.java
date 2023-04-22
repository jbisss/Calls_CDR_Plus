package ru.learnup.nexigntask.callscdrplus.service;

import org.springframework.stereotype.Service;
import ru.learnup.nexigntask.callscdrplus.entity.Tariff;
import ru.learnup.nexigntask.callscdrplus.repository.TariffRepository;

@Service
public class TariffService {

    private final TariffRepository tariffRepository;

    public TariffService(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    public Tariff getTariffById(String tariffId){
        return tariffRepository.findTariffByTariffId(tariffId);
    }
}
