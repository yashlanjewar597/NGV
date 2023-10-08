package com.nextgenventures.demo;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class TimeComparison {
    public int getCustomDayOfWeek() {
        // Implement your custom mapping of DayOfWeek to 0, 1, 2, ...
        // Example mapping: Sunday (DayOfWeek.SUNDAY) -> 0, Monday (DayOfWeek.MONDAY) -> 1, and so on.
        ZonedDateTime currentDateTime = ZonedDateTime.now(ZoneId.of("UTC"));

        DayOfWeek dayOfWeek = currentDateTime.getDayOfWeek();

        switch (dayOfWeek) {
            case SUNDAY: return 0;
            case MONDAY: return 1;
            case TUESDAY: return 2;
            case WEDNESDAY: return 3;
            case THURSDAY: return 4;
            case FRIDAY: return 5;
            case SATURDAY: return 6;
            default: return -1; // Return -1 for any unrecognized day of the week
        }
    }

    public void compareTime(String startDateTimeStr, String endDateTimeStr) {
        // Parse the start and end time strings to OffsetTime objects
        OffsetTime startTime = OffsetTime.parse(startDateTimeStr);
        OffsetTime endTime = OffsetTime.parse(endDateTimeStr);

        // Get the current local time
        LocalTime currentTime = LocalTime.now();

        // Convert the current local time to UTC
        OffsetTime currentUTCTime = currentTime.atOffset(ZoneOffset.UTC);

        // Get the custom day of the week representation

        // Compare the current UTC time with the start and end times
        if (currentUTCTime.isAfter(startTime) && currentUTCTime.isBefore(endTime)) {
            System.out.println("The current local time falls within the specified time range on custom day ");
        } else {
            System.out.println("The current local time is outside the specified time range on custom day ");
        }
    }

    public static void main(String[] args) {
        // Create an instance of TimeComparisonExample
        TimeComparison timeComparisonExample = new TimeComparison();

        // Assume you have start and end time strings as variables
        String startDateTimeStr = "07:00:00+00:00";
        String endDateTimeStr = "21:00:00+00:00";

        // Call the compareTime method and pass the variables
        timeComparisonExample.compareTime(startDateTimeStr, endDateTimeStr);
    }
}
