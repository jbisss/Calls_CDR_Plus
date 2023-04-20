package ru.learnup.nexigntask.callscdrplus.enums;

public enum Tariff {
    UNLIMITED("06"),
    MINUTE_BY_MINUTE("03"),
    DEFAULT("11");
    private final String code;

    Tariff(String code) {
        this.code = code;
    }

    public static Tariff getTariffByCode(String code) {
        for (Tariff tariff : Tariff.values()) {
            if (tariff.getCode().equals(code)) {
                return tariff;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }
}
