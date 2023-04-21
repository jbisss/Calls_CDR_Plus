package ru.learnup.nexigntask.callscdrplus.dto.charge;

import lombok.Data;

import java.util.List;

@Data
public class ChargeResponseDto {

    private final List<ChargeNumberBalanceDto> numbers;
}
