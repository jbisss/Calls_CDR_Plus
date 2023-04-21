package ru.learnup.nexigntask.callscdrplus.controllers.dto.chargetariff;

import lombok.Data;

@Data
public class ChangeTariffRequestDto {

    private final String numberPhone;
    private final String tariffId;
}
