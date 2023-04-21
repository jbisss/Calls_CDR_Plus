package ru.learnup.nexigntask.callscdrplus.dto.changetariff;

import lombok.Data;

@Data
public class ChangeTariffRequestDto {

    private final String numberPhone;
    private final String tariffId;
}
