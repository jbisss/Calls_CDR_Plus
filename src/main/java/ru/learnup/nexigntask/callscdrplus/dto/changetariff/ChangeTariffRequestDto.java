package ru.learnup.nexigntask.callscdrplus.dto.changetariff;

import lombok.Data;

/**
 * Dto класс, принимает данные из запроса о смене тарифа
 */
@Data
public class ChangeTariffRequestDto {

    private final String phoneNumber;
    private final String tariffId;
}
