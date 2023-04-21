package ru.learnup.nexigntask.callscdrplus.controllers.dto.addbalance;

import lombok.Data;

@Data
public class AddBalanceResponseDto {

    private final long id;
    private final String numberPhone;
    private final int money;
}
