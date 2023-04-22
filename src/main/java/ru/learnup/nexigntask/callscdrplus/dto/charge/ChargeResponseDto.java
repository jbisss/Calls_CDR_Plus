package ru.learnup.nexigntask.callscdrplus.dto.charge;

import lombok.Data;

import java.util.List;

/**
 * Dto класс, отправляет данные-коллекцию (номер, баланс) в на запрос о смене тарифа
 */
@Data
public class ChargeResponseDto {

    private final List<ChargeNumberBalanceDto> numbers;
}
