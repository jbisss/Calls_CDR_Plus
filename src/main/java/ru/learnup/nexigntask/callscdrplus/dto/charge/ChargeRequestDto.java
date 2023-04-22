package ru.learnup.nexigntask.callscdrplus.dto.charge;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChargeRequestDto {

    private final String action;

    public ChargeRequestDto(){
        this.action = "";
    }
}
