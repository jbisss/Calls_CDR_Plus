package ru.learnup.nexigntask.callscdrplus.controllers.dto.charge;

import lombok.Data;

import java.util.List;

@Data
public class ChargeResponseDto {

    private final List<ChargeNumberBalanceDto> numbers;
}
