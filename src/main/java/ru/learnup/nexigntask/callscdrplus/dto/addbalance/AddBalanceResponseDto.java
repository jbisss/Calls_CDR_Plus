package ru.learnup.nexigntask.callscdrplus.dto.addbalance;

import lombok.Data;

@Data
public class AddBalanceResponseDto {

    private final long id;
    private final String numberPhone;
    private final double money;
}
