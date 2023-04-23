package ru.learnup.nexigntask.callscdrplus.dao;

import lombok.Data;
import ru.learnup.nexigntask.callscdrplus.entities.Tariff;

@Data
public class NumberTariff {
    private final String number;
    private final Tariff tariffId;

    public NumberTariff(String number, Tariff tariffId) {
        this.number = number;
        this.tariffId = tariffId;
    }
}
