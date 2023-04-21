package ru.learnup.nexigntask.callscdrplus.dto.newabonent;

import lombok.Data;

@Data
public class NewAbonentRequestResponseDto {

    private final String numberPhone;
    private final String tariffId;
    private final int balance;
}
