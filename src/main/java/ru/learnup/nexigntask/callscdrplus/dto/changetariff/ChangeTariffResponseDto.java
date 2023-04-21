package ru.learnup.nexigntask.callscdrplus.dto.changetariff;

import lombok.Data;

@Data
public class ChangeTariffResponseDto {

    private final long id;
    private final String numberPhone;
    private final String tariffId;
}
