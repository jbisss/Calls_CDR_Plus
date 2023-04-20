package ru.learnup.nexigntask.callscdrplus.pojoclasses.dbresults;

import lombok.Data;
import ru.learnup.nexigntask.callscdrplus.entity.Tariff;

@Data
public class NumberTariff {
    private final String number;
    private final Tariff tariffId;

    public NumberTariff(String number, Tariff tariffId) {
        this.number = number;
        this.tariffId = tariffId;
    }
}
