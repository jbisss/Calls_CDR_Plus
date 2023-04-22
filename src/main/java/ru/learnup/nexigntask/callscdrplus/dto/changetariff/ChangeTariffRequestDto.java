package ru.learnup.nexigntask.callscdrplus.dto.changetariff;

import lombok.Data;

@Data
public class ChangeTariffRequestDto {

    private final String phoneNumber;
    private final String tariffId;
}
