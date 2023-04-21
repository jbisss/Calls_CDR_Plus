package ru.learnup.nexigntask.callscdrplus.controllers.dto.addbalance;

import lombok.Data;

@Data
public class AddBalanceRequestDto {

    private final String phoneNumber;
    private final int money;
}
