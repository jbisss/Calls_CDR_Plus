package ru.learnup.nexigntask.callscdrplus.controllers.dto.newabonent;

import lombok.Data;

@Data
public class NewAbonentRequestResponseDto {

    private final String numberPhone;
    private final String tariffId;
    private final int balance;
}
