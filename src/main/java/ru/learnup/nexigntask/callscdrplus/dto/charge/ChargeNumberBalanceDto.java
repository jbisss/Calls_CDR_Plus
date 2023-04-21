package ru.learnup.nexigntask.callscdrplus.dto.charge;

import lombok.Data;

@Data
public class ChargeNumberBalanceDto {

    private final String phoneNumber;
    private final int balance;
}
