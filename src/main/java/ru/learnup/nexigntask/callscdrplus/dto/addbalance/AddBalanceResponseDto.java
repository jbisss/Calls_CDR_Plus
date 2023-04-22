package ru.learnup.nexigntask.callscdrplus.dto.addbalance;

import lombok.Data;

/**
 * Dto класс, отправляет данные в ответ на запрос о пополнении баланса
 */
@Data
public class AddBalanceResponseDto {

    private final long id;
    private final String numberPhone;
    private final double money;
}
