package ru.learnup.nexigntask.callscdrplus.dto.charge;

import lombok.Getter;
import lombok.Setter;

/**
 * Dto класс, принимает данные из запроса о тарификации
 */
@Getter
@Setter
public class ChargeRequestDto {

    private final String action;

    public ChargeRequestDto(){
        this.action = "";
    }
}
