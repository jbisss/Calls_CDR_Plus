package ru.learnup.nexigntask.callscdrplus.controllers.dto.charge;

import lombok.Data;

@Data
public class ChargeNumberBalanceDto {

    private final String phoneNumber;
    private final int balance;
}
