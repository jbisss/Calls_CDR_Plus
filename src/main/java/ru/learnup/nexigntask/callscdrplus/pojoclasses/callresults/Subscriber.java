package ru.learnup.nexigntask.callscdrplus.pojoclasses.callresults;

import ru.learnup.nexigntask.callscdrplus.enums.CallCode;
import ru.learnup.nexigntask.callscdrplus.enums.Tariff;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Subscriber {
    public static HashMap<String, Subscriber> subscribers = new HashMap<>();
    private final String number;
    private final Tariff tariff;
    private final ArrayList<Call> calls = new ArrayList<>();
    private double totalCost;

    public Subscriber(String number, Tariff tariff) {
        this.number = number;
        this.tariff = tariff;
        this.totalCost = 0;
        if (tariff == Tariff.UNLIMITED) {
            totalCost += 100;
        }
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
    public void countCallsCost() {
        List<Call> sortedCalls = calls.stream()
                .sorted(Comparator.comparing(Call::getStartTime)).toList();
        long totalDuration = 0;
        int minutesTotal = 0;
        int minutesCall;
        double price = 0;
        for (Call call : sortedCalls) {
            minutesCall = (int) (call.getDurationNumber() / 60);
            if (tariff == Tariff.DEFAULT) {
                if (call.getCallCode() == CallCode.CALL_OUT) {
                    if (minutesTotal > 100) {
                        price = minutesCall * 1.5d;
                        call.setCost(price);
                    } else if (minutesTotal < 100 && minutesTotal + minutesCall > 100) {
                        price = ((100 - minutesTotal) * 0.5d) + ((minutesTotal + minutesCall - 100) * 1.5d);
                        call.setCost(price);
                    } else {
                        price = minutesCall * 0.5d;
                        call.setCost(price);
                    }
                }
            } else if (tariff == Tariff.UNLIMITED) {
                if (minutesTotal > 300) {
                    price = (minutesCall) * 1.0d;
                    call.setCost(price);
                } else if (minutesTotal < 300 && minutesTotal + minutesCall > 300) {
                    price = ((minutesTotal + minutesCall - 300) * 1.0d);
                    call.setCost(price);
                }
            } else if (tariff == Tariff.MINUTE_BY_MINUTE) {
                price = minutesCall * 1.5d;
                call.setCost(price);
            }
            totalDuration += call.getDurationNumber();
            minutesTotal = (int) (totalDuration / 60);
            this.totalCost += price;
            price = 0;
        }
    }

    /**
     * @return String representation of number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @return total cost of calls
     */
    public double getTotalCost() {
        return totalCost;
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
        resultBuilder.append(this.tariff.getCode());
        resultBuilder.append("\n");
        resultBuilder.append(line);
        resultBuilder.append("Report for phone number ");
        resultBuilder.append(this.number);
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
        if (this.totalCost < 100) {
            resultBuilder.append(" ");
        }
        resultBuilder.append(String.format("%3.2f", this.totalCost));
        resultBuilder.append(" rubles |\n");
        resultBuilder.append(line);
        return resultBuilder.toString();
    }
}

