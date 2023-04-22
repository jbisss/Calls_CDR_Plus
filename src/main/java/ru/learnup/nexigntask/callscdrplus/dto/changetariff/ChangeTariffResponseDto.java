package ru.learnup.nexigntask.callscdrplus.dto.changetariff;

import lombok.Data;

/**
 * Dto класс, отправляет данные в ответ на запрос о смене тарифа
 */
@Data
public class ChangeTariffResponseDto {

    private final long id;
    private final String numberPhone;
    private final String tariffId;
}
