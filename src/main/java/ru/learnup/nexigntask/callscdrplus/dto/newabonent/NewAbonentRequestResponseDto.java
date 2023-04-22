package ru.learnup.nexigntask.callscdrplus.dto.newabonent;

import lombok.Data;

/**
 * Dto класс, принимает и отправялет данные на запрос о внесении нового абонента
 */
@Data
public class NewAbonentRequestResponseDto {

    private final String numberPhone;
    private final String tariffId;
    private final int balance;
}
