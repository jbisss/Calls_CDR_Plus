package ru.learnup.nexigntask.callscdrplus.dto.addbalance;

import lombok.Data;

/**
 * Dto класс, принимает данные из запроса о пополнении баланса
 */
@Data
public class AddBalanceRequestDto {

    private final String phoneNumber;
    private final int money;
}
