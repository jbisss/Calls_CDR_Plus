package ru.learnup.nexigntask.callscdrplus.dto.numberdetail;

import lombok.Data;
import ru.learnup.nexigntask.callscdrplus.dto.getnumberdetails.Call;

import java.util.ArrayList;

/**
 * Dto класс, отправялет данные о клиенте
 */
@Data
public class NumberDetailResponseDto {

    private final long id;
    private final String phoneNumber;
    private final String tariffIndex;
    private final ArrayList<Call> payLoad;
}
