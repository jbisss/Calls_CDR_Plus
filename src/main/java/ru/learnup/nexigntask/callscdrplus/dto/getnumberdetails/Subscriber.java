package ru.learnup.nexigntask.callscdrplus.dto.getnumberdetails;

import lombok.Getter;
import lombok.Setter;
import ru.learnup.nexigntask.callscdrplus.entity.Client;
import ru.learnup.nexigntask.callscdrplus.enums.CallCode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Каждому клиенту соответствует Subscriber - в этом классе производится тарификация,
 * то есть расчёт стоимости всех звонков клиента
 * Также этот класс используется в качестве ответа на http-запрос об информации о
 * клиента (со списком звонком)
 */
@Getter
public class Subscriber {

    private final String number;
    @Setter
    private String tariff;
    private final List<Call> calls = new ArrayList<>();
    private double totalCost;

    public Subscriber(String number) {
        this.number = number;
        this.totalCost = 0;
    }

    /**
     * Adds a new call to calls list of subscriber
     *
     * @param code      code of call
     * @param startTime start time of call
     * @param endTime   end time of call
     */
    public void addCall(String code, String startTime, String endTime) {
        calls.add(new Call(CallCode.getCallCodeByCode(code), startTime, endTime));
    }

    /**
     * Counts a cost of each call
     */
    public void countCallsCost(Client client) {
        List<Call> sortedCalls = calls.stream()
                .sorted(Comparator.comparing(Call::getStartTime)).toList();
        int minutesCall;
        double price = 0;
        tariff = client.getTariff().getTariffId();
        totalCost += client.getTariff().getSubscriptionFee();
        for (Call call : sortedCalls) {
            int flag = (int) call.getDurationDecimal() % 60;
            if(flag > 0) {
                minutesCall = (int)(call.getDurationDecimal() / 60) + 1;
            } else {
                minutesCall = (int)(call.getDurationDecimal() / 60);
            }
            int benefitMinutesLeft = client.getBenefitMinutesLeft();
            int benefitMinutes = Math.min(benefitMinutesLeft, minutesCall);
            int defaultMinutes = (benefitMinutesLeft > minutesCall) ? 0 : minutesCall - benefitMinutesLeft;
            if(call.getCallCode() == CallCode.CALL_IN) {
                price = benefitMinutes * client.getTariff().getInCallCostId().getBenefitMinuteCost()
                        + defaultMinutes * client.getTariff().getInCallCostId().getDefaultMinuteCost();
                if(client.getBenefitMinutesLeft() != 0) {
                    client.setBenefitMinutesLeft(benefitMinutesLeft - benefitMinutes);
                }
            }
            if(call.getCallCode() == CallCode.CALL_OUT) {
                price = benefitMinutes * client.getTariff().getOutCallCostId().getBenefitMinuteCost()
                        + defaultMinutes * client.getTariff().getOutCallCostId().getDefaultMinuteCost();
                if(client.getBenefitMinutesLeft() != 0) {
                    client.setBenefitMinutesLeft(benefitMinutesLeft - benefitMinutes);
                }
            }
            call.setCost(price);
            totalCost += price;
            price = 0;
        }
        client.setBalance(client.getBalance() - totalCost);
    }

    /**
     * @return String representation of number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Represents an information about calls in String format
     *
     * @param calls list of calls
     * @return String representation of calls
     */
    public String buildCallString(List<Call> calls) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Call call : calls) {
            stringBuilder.append("|     ");
            stringBuilder.append(call.getCallCode().getCode());
            stringBuilder.append("    | ");
            stringBuilder.append(call.getStartTime());
            stringBuilder.append(" | ");
            stringBuilder.append(call.getEndTime());
            stringBuilder.append(" | ");
            stringBuilder.append(call.getDuration());
            stringBuilder.append(" |  ");
            if (call.getCost() < 10) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(String.format("%.2f", call.getCost()));
            stringBuilder.append(" |\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Build a String representation of table where all information about phone number is represented
     *
     * @return String representation of information about subscriber
     */
    @Override
    public String toString() {
        String line = "-----------------------------------------------------------------------------\n";
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("Tariff index: ");
        resultBuilder.append(tariff);
        resultBuilder.append("\n");
        resultBuilder.append(line);
        resultBuilder.append("Report for phone number ");
        resultBuilder.append(number);
        resultBuilder.append(":\n");
        resultBuilder.append(line);
        resultBuilder.append("| Call Type |      Start Time     |       End Time      | Duration |  Cost  |\n");
        resultBuilder.append(line);
        List<Call> callOut = calls.stream()
                .filter(call -> call.getCallCode() == CallCode.CALL_OUT)
                .sorted(Comparator.comparing(Call::getStartTime))
                .collect(Collectors.toList());
        List<Call> callIn = calls.stream()
                .filter(call -> call.getCallCode() == CallCode.CALL_IN)
                .sorted(Comparator.comparing(Call::getStartTime))
                .collect(Collectors.toList());
        resultBuilder.append(buildCallString(callOut));
        resultBuilder.append(buildCallString(callIn));
        resultBuilder.append(line);
        resultBuilder.append("|                                           Total Cost: |     ");
        if (totalCost < 100) {
            resultBuilder.append(" ");
        }
        resultBuilder.append(String.format("%3.2f", totalCost));
        resultBuilder.append(" rubles |\n");
        resultBuilder.append(line);
        return resultBuilder.toString();
    }
}

