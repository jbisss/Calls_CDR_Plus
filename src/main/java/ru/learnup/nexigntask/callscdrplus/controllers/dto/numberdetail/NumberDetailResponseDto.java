package ru.learnup.nexigntask.callscdrplus.controllers.dto.numberdetail;

import lombok.Data;
import ru.learnup.nexigntask.callscdrplus.pojo.callresults.Call;

import java.util.ArrayList;

@Data
public class NumberDetailResponseDto {

    private final long id;
    private final String phoneNumber;
    private final String tariffIndex;
    private final ArrayList<Call> payLoad;
}
