package ru.learnup.nexigntask.callscdrplus.dto.charge;

import lombok.Data;

/**
 * Dto класс, для отправки данных (номер, баланс)
 */
@Data
public class ChargeNumberBalanceDto {

    private final String phoneNumber;
    private final double balance;
}
