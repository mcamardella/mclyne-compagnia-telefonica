package com.mcamardella.core.utility;

import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

@UtilityClass
public class Utility {
    static final int MINUTES_PER_HOUR = 60;
    static final int SECONDS_PER_MINUTE = 60;
    static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

    public static Long durationSeconds(LocalDateTime dateCallStart, LocalDateTime dateCallEnd) {
        LocalDateTime toDateTime = dateCallEnd;
        LocalDateTime fromDateTime = dateCallStart;

        Period period = getPeriod(fromDateTime, toDateTime);
        long[] time = getTime(fromDateTime, toDateTime);

        //String durationString = period.getYears() + ":" + period.getMonths() + ":" + period.getDays() + ":" + time[0] + ":" + time[1] + ":" + time[2];
        /*return period.getYears() + " years " +
                period.getMonths() + " months " +
                period.getDays() + " days " +
                time[0] + " hours " +
                time[1] + " minutes " +
                time[2] + " seconds.";*/

        Long secondsYear;
        Long secondsMonths;
        Long secondsDays;

        Long secondsHours = time[0] * (60*60);
        Long secondsMinutes = time[1] * 60;
        Long secondsSeconds = time[2];

        return secondsHours + secondsMinutes + secondsSeconds;

    }

    public static String durationToSring(LocalDateTime dateCallStart, LocalDateTime dateCallEnd) {
        LocalDateTime toDateTime = dateCallEnd;
        LocalDateTime fromDateTime = dateCallStart;

        Period period = getPeriod(fromDateTime, toDateTime);
        long[] time = getTime(fromDateTime, toDateTime);

        return period.getYears() + " years " +
                period.getMonths() + " months " +
                period.getDays() + " days " +
                time[0] + " hours " +
                time[1] + " minutes " +
                time[2] + " seconds.";
    }

    private static Period getPeriod(LocalDateTime dob, LocalDateTime now) {
        return Period.between(dob.toLocalDate(), now.toLocalDate());
    }

    private static long[] getTime(LocalDateTime dob, LocalDateTime now) {
        LocalDateTime today = LocalDateTime.of(now.getYear(),
                now.getMonthValue(), now.getDayOfMonth(), dob.getHour(), dob.getMinute(), dob.getSecond());
        Duration duration = Duration.between(today, now);

        long seconds = duration.getSeconds();

        long hours = seconds / SECONDS_PER_HOUR;
        long minutes = ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
        long secs = (seconds % SECONDS_PER_MINUTE);

        return new long[]{hours, minutes, secs};
    }
}
