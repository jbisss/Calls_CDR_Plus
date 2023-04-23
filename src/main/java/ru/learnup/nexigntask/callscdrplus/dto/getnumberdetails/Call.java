package ru.learnup.nexigntask.callscdrplus.dto.getnumberdetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import ru.learnup.nexigntask.callscdrplus.enums.CallCode;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Getter
@Setter
public class Call {

    private final CallCode callCode;
    private String startTime;
    private String endTime;
    private String duration;
    private double cost;
    @JsonIgnore
    private LocalDateTime startDateTime;
    @JsonIgnore
    private LocalDateTime endDateTime;
    @JsonIgnore
    private long durationDecimal;

    /**
     * @param callCode  code of call
     * @param startTime call's start time
     * @param endTime   call's end time
     */
    public Call(CallCode callCode, String startTime, String endTime) {
        this.callCode = callCode;
        this.startTime = startTime;
        this.endTime = endTime;
        try {
            countDuration(startTime, endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Call(CallCode callCode, LocalDateTime startTime, LocalDateTime endTime) {
        this.callCode = callCode;
        this.startDateTime = startTime;
        this.endDateTime = endTime;
    }

    /**
     * Converts time to a right format
     *
     * @param time time in incorrect format (yyyyMMddHHmmss)
     * @return time in correct format (yyyy-MM-dd HH:mm:ss)
     */
    private String buildDate(String time) {
        StringBuilder resultDate = new StringBuilder();
        for (int i = 0; i < time.length(); i++) {
            resultDate.append(time.charAt(i));
            if (i == 3 || i == 5) {
                resultDate.append("-");
            }
            if (i == 7) {
                resultDate.append(" ");
            }
            if (i == 9 || i == 11) {
                resultDate.append(":");
            }
        }
        return resultDate.toString();
    }

    /**
     * Numbers which are less than 10 should be represented with a starting zero (6 -> '06')
     *
     * @param x a number
     * @return a number as a String in right format (9 -> '09'; 10 -> '10')
     */
    private String makeNumberString(int x) {
        if (x < 10) {
            return "0" + x;
        } else {
            return Integer.toString(x);
        }
    }

    /**
     * Converts a time in seconds to a String in HH:mm:ss format
     *
     * @param duration in seconds
     * @return String in format HH:mm:ss
     */
    private String convertDurationToString(long duration) {
        int hours = (int) (duration / 3600);
        duration = duration % 3600;
        int minutes = (int) (duration / 60);
        duration = duration % 60;
        int seconds = (int) duration;
        return makeNumberString(hours) + ":" + makeNumberString(minutes) + ":" + makeNumberString(seconds);
    }

    /**
     * Counts duration of a call and stores a String and Numeric representation of duration
     *
     * @param startTime time when the call was started
     * @param endTime   time when the call was ended
     * @throws ParseException if date can not be parsed
     */
    private void countDuration(String startTime, String endTime) throws ParseException {
        this.startTime = buildDate(startTime);
        this.endTime = buildDate(endTime);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long duration = (dateFormat.parse(this.endTime).getTime() - dateFormat.parse(this.startTime).getTime()) / 1000;
        this.durationDecimal = duration;
        this.duration = convertDurationToString(duration);
    }
}
