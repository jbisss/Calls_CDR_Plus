package ru.learnup.nexigntask.callscdrplus.enums;

public enum CallCode {
    CALL_IN("02"),
    CALL_OUT("01");
    private final String code;

    CallCode(String code) {
        this.code = code;
    }

    public static CallCode getCallCodeByCode(String code) {
        for (CallCode callCode : CallCode.values()) {
            if (callCode.getCode().equals(code)) {
                return callCode;
            }
        }
        return null;
    }

    public String getCode() {
        return this.code;
    }
}
