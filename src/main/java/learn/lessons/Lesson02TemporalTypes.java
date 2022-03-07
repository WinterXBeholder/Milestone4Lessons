package learn.lessons;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lesson02TemporalTypes {
    public static void dateAndTime() {
        /* All temporal types that are prefixed with "Local" represent dates and times that are not time zone aware.
        They can't tell us that 4PM in one time zone is 1PM in another. Instead, they give us an easy-to-use API for
        building, adding, subtracting, and comparing dates and times that are assumed to be in the same time zone. */

        /* LocalDate represents a year, month, and day without time. It doesn't expose a public constructor. Instead,
        it uses static builder methods to create an instance. */
        // Use the class name, LocalDate, to access builder methods.
        LocalDate juneFirst = LocalDate.of(2020, 6, 1);       // numeric year, month, day
        LocalDate julyTwelfth = LocalDate.of(2020, Month.JULY, 12); // month enum
        LocalDate today = LocalDate.now();                                          // current year, month, day
        // By default, a LocalDate's string representation is in ISO format, year-month-day, "YYYY-MM-DD".
        System.out.println(juneFirst);   // 2020-06-01
        System.out.println(julyTwelfth); // 2020-07-12
        System.out.println(today);       // 2020-05-26
        /* LocalDate contains getter methods for time units like year, month, day of month, and day of year. Some
        getters return numeric values and some return enums. */
        System.out.println(julyTwelfth.getYear());       // 2020
        System.out.println(julyTwelfth.getMonth());      // JULY (Month enum)
        System.out.println(julyTwelfth.getMonthValue()); // 7
        System.out.println(julyTwelfth.getDayOfMonth()); // 12
        System.out.println(julyTwelfth.getDayOfYear());  // 194
        System.out.println(julyTwelfth.getDayOfWeek());  // SUNDAY (DayOfWeek enum)

        today = LocalDate.now();
        System.out.println(today);                    // 2020-05-26
        LocalDate weekFromToday = today.plusWeeks(1); // new instance
        System.out.println(today);                    // 2020-05-26 <-- `today` didn't change
        System.out.println(weekFromToday);            // 2020-06-02



        today = LocalDate.now();
        System.out.println(today);                  // 2020-05-26
        System.out.println(today.plusYears(12));    // 2032-05-26
        System.out.println(today.plusMonths(100));  // 2028-09-26
        System.out.println(today.plusWeeks(27));    // 2020-12-01
        System.out.println(today.plusDays(10000));  // 2047-10-12
        System.out.println(today.minusYears(12));   // 2008-05-26
        System.out.println(today.minusMonths(100)); // 2012-01-26
        System.out.println(today.minusWeeks(27));   // 2019-11-19
        System.out.println(today.minusDays(10000)); // 1993-01-08
        // Negative arguments
        System.out.println(today.plusDays(-100));   // 2020-02-16
        System.out.println(today.minusDays(-100));  // 2020-09-03


        /* To find the number of years, months, and days between two LocalDates, use the Period class.
        A Period can also be used to add or subtract from a LocalDate. */
        LocalDate start = LocalDate.of(2011, 4, 22);
        LocalDate end = LocalDate.of(2025, 11, 7);
        today = LocalDate.now();
        // Approaches A and B are equivalent. Choose one.
        // A. instance method
        Period difference = start.until(end);
        // B. static method
        difference = Period.between(start, end);
        System.out.println(difference.getYears());   // 14
        System.out.println(difference.getMonths());  // 6
        System.out.println(difference.getDays());    // 16
        // Add and subtract using a Period.
        System.out.println(today.plus(difference));  // 2034-12-12
        System.out.println(today.minus(difference)); // 2005-11-10


        // Given a random day in August of the current year, list two weeks of weekdays starting with the date. Skip
        // weekends.
        Random rand = new Random();
        int year = LocalDate.now().getYear();
        int dayOfMonth = rand.nextInt(Month.AUGUST.maxLength()) + 1;
        LocalDate day = LocalDate.of(year, Month.AUGUST, dayOfMonth);
        LocalDate stop = day.plusWeeks(2);
        for( ; day.compareTo(stop) < 0; day = day.plusDays(1)) {
            if (day.getDayOfWeek() != DayOfWeek.SATURDAY && day.getDayOfWeek() != DayOfWeek.SUNDAY) {
                System.out.printf("%s: %s%n", day.getDayOfWeek(), day);
            }
        }



        // Given a start date, an end date, and a list of booked dates, return a list of dates that are still available.
        LocalDate begin = LocalDate.of(2023, 10, 24);
        end = begin.plusWeeks(3);
        // make up some booked dates
        List<LocalDate> notAvailable = getRandomDates(begin, end);
        // calculate the dates available
        for (LocalDate date : getAvailableDates(begin, end, notAvailable)) {
            System.out.printf("%s: %s%n", date.getDayOfWeek(), date);
        }





        /* LocalTime represents a time without a date in hours, minutes, seconds, and nanoseconds. It
        favors static builder methods to create an instance. PM values are expressed using 24-hour
        values (also called Military Time): add twelve to all PM hours. e.g. 8:00PM equals 20:00,
        9:27PM equals 21:27, Midnight equals 24:00. */
        LocalTime elevenFifteenAM = LocalTime.of(11, 15); // AM
        LocalTime elevenFifteenPM = LocalTime.of(23, 15); // PM
        LocalTime now = LocalTime.now();
        // By default, a LocalTime's string representation is in ISO format, hour:minute:second.nanosecond, "HH:mm:ss.n"
        System.out.println(elevenFifteenAM); // 11:15
        System.out.println(elevenFifteenPM); // 23:15
        System.out.println(now);             // 16:56:01.633058800
        System.out.println(elevenFifteenPM.getHour());   // 23
        System.out.println(elevenFifteenPM.getMinute()); // 15
        System.out.println(elevenFifteenPM.getSecond()); // 0
        System.out.println(elevenFifteenPM.getNano());   // 0
        System.out.println(now.getHour());   // 16
        System.out.println(now.getMinute()); // 56
        System.out.println(now.getSecond()); // 1
        System.out.println(now.getNano());   // 633058800


        /* Plus and minus methods create a new LocalTime. They don't alter the existing time. Values that are too large
        to be contained in a day "wrap around". Adding 48 hours does nothing. */
        now = LocalTime.now();
        System.out.println(now);                   // 17:09:24.393201700
        System.out.println(now.plusHours(2));      // 19:09:24.393201700
        System.out.println(now.plusMinutes(64));   // 18:13:24.393201700
        System.out.println(now.plusSeconds(35));   // 17:09:59.393201700
        System.out.println(now.plusNanos(100123)); // 17:09:24.393301823
        // adding 48 hours wraps around to the original time
        System.out.println(now.plusHours(48));     // 17:09:24.393201700

        /* Where dates represent their differences using the Period class. Time represents differences with the
        Duration class. A Duration holds hours, minutes, seconds, and nanoseconds, just like LocalTime. But instead of
        representing a moment in time, it represents a duration of time.
        Where Period uses get[Unit] methods, Duration uses to[Unit]Part and to[Unit] to retrieve individual time units. */
        LocalTime one = LocalTime.of(3, 45, 32);
        LocalTime two = LocalTime.of(21, 12, 5);
        Duration durationDifference = Duration.between(one, two);
        // Taken together, the `Part` methods add up to a duration.
        System.out.println(durationDifference.toHoursPart());   // 17
        System.out.println(durationDifference.toMinutesPart()); // 26
        System.out.println(durationDifference.toSecondsPart()); // 33
        System.out.println(durationDifference.toNanosPart());   // 0
        // Non-Part methods give unit totals.
        System.out.println(durationDifference.toHours());   // 17
        System.out.println(durationDifference.toMinutes()); // 1046
        System.out.println(durationDifference.toSeconds()); // 62793


        /* Starting at 7:30 AM, print 20-minute appointment times until lunch, but do not allow an appointment to end
        after noon. Then print 20 minute appointments from 1:00 PM until 5:30 PM. Again, not allowing an appointment to
        extend past 5:30 PM. */
        final LocalTime noon = LocalTime.of(12, 0);
        final LocalTime quittingTime = LocalTime.of(17, 30);
        Duration appointmentDuration = Duration.ofMinutes(20);
        LocalTime timeStart = LocalTime.of(7, 30);
        LocalTime timeEnd = timeStart.plus(appointmentDuration);
        System.out.println("Appointments before lunch:");
        while (!timeEnd.isAfter(noon)) {
            System.out.printf("%s - %s%n", timeStart, timeEnd);
            timeStart = timeEnd;
            timeEnd = timeStart.plus(appointmentDuration);
        }

        timeStart = LocalTime.of(13, 0); // 1:00PM
        timeEnd = timeStart.plus(appointmentDuration);

        System.out.println("Appointments after lunch:");
        while (!timeEnd.isAfter(quittingTime)) {
            System.out.printf("%s - %s%n", timeStart, timeEnd);
            timeStart = timeEnd;
            timeEnd = timeStart.plus(appointmentDuration);
        }




        /* Temporal types are never stored with a specific format. Under the hood, they're numbers. Formats are applied
        when needed. They're not part of the date or time.
        To take full control of a format and leave the default ISO format behind, we use the DateTimeFormatter class.
        It comes with several default formatters, but most of them are ISO. To build a customized format, we use a
        format pattern. */

        LocalDateTime dateTimeNow = LocalDateTime.of(2020, 7, 8, 17, 15);

        DateTimeFormatter fourDigitYear = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter twoDigitYear = DateTimeFormatter.ofPattern("yy");
        DateTimeFormatter oneDigitMonth = DateTimeFormatter.ofPattern("M");
        DateTimeFormatter twoDigitMonth = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter monthAbbr = DateTimeFormatter.ofPattern("MMM");
        DateTimeFormatter monthName = DateTimeFormatter.ofPattern("MMMM");
        System.out.println(dateTimeNow.format(fourDigitYear)); // 2020
        System.out.println(dateTimeNow.format(twoDigitYear));  // 20
        System.out.println(dateTimeNow.format(oneDigitMonth)); // 7
        System.out.println(dateTimeNow.format(twoDigitMonth)); // 07
        System.out.println(dateTimeNow.format(monthAbbr));     // Jul
        System.out.println(dateTimeNow.format(monthName));     // July

        DateTimeFormatter oneDigitDayOfMonth = DateTimeFormatter.ofPattern("d");
        DateTimeFormatter twoDigitDayOfMonth = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter dayOfYear = DateTimeFormatter.ofPattern("D");
        DateTimeFormatter dayOfWeekAbbr = DateTimeFormatter.ofPattern("eee");
        DateTimeFormatter dayOfWeekName = DateTimeFormatter.ofPattern("eeee");
        System.out.println(dateTimeNow.format(oneDigitDayOfMonth)); // 8
        System.out.println(dateTimeNow.format(twoDigitDayOfMonth)); // 08
        System.out.println(dateTimeNow.format(dayOfYear));          // 190
        System.out.println(dateTimeNow.format(dayOfWeekAbbr));      // Wed
        System.out.println(dateTimeNow.format(dayOfWeekName));      // Wednesday

        DateTimeFormatter twelveHourOneDigit = DateTimeFormatter.ofPattern("h");
        DateTimeFormatter twelveHourTwoDigit = DateTimeFormatter.ofPattern("hh");
        DateTimeFormatter twentyFourHour = DateTimeFormatter.ofPattern("kk");
        DateTimeFormatter twelveHourTwoDigitAMPM = DateTimeFormatter.ofPattern("hha");
        DateTimeFormatter minutesTwoDigits = DateTimeFormatter.ofPattern("mm");
        DateTimeFormatter secondsTwoDigits = DateTimeFormatter.ofPattern("ss");
        System.out.println(dateTimeNow.format(twelveHourOneDigit));     // 5
        System.out.println(dateTimeNow.format(twelveHourTwoDigit));     // 05
        System.out.println(dateTimeNow.format(twentyFourHour));         // 17
        System.out.println(dateTimeNow.format(twelveHourTwoDigitAMPM)); // 05PM
        System.out.println(dateTimeNow.format(minutesTwoDigits));       // 15
        System.out.println(dateTimeNow.format(secondsTwoDigits));       // 00

        /* Symbol sequences can be combined to build complex patterns. Non-symbol characters like /, -, and . are
        embedded directly in the output. To embedded symbols with meaning, surround them with a single quote. */
        DateTimeFormatter usDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter usTime = DateTimeFormatter.ofPattern("h:mma");
        DateTimeFormatter usDateTime = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mma");
        DateTimeFormatter monthAbbrDate = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        DateTimeFormatter dotDate = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        DateTimeFormatter formalDate = DateTimeFormatter.ofPattern("d MMMM, yyyy");
        // The first two M's have meaning. The third M, surrounded
        // by single quotes is embedded directly in the output.
        DateTimeFormatter withLiteral = DateTimeFormatter.ofPattern("yy.MM'M'.dd");
        System.out.println(dateTimeNow.format(usDate));        // 07/08/2020
        System.out.println(dateTimeNow.format(usTime));        // 5:15PM
        System.out.println(dateTimeNow.format(usDateTime));    // 07/08/2020 5:15PM
        System.out.println(dateTimeNow.format(monthAbbrDate)); // 08-Jul-2020
        System.out.println(dateTimeNow.format(dotDate));       // 2020.07.08
        System.out.println(dateTimeNow.format(formalDate));    // 8 July, 2020
        System.out.println(dateTimeNow.format(withLiteral));   // 20.07M.08

        /* Once we have a formatter, we can use it to parse a String into a LocalTime, LocalDate, or LocalDateTime. The
        string must match the format exactly or the parse method will throw an exception */
        Scanner console = new Scanner(System.in);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mma");
        System.out.print("Enter a date in month/day/year format:");
        LocalDate date = LocalDate.parse(console.nextLine(), dateFormat);
        System.out.println(date); // default format
        System.out.print("Enter a time in twelve hour:minute format:");
        LocalTime time = LocalTime.parse(console.nextLine(), timeFormat);
        System.out.println(time); // default format
        System.out.println(time); // default format

        dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
        try {
            date = LocalDate.parse(console.nextLine(), dateFormat);
            System.out.println(date);
        } catch (DateTimeParseException ex) {
            System.out.printf("'%s' is not a valid date.%n", date);
        }
    }



    static List<LocalDate> getRandomDates(LocalDate start, LocalDate end) {
        Random rand = new Random();
        ArrayList<LocalDate> result = new ArrayList<>();
        for (LocalDate current = start; current.compareTo(end) <= 0; current = current.plusDays(1)) {
            if (rand.nextDouble() < 0.7) {
                result.add(current);
            }
        }
        return result;
    }

    // Given a start date, an end date, and a list of booked dates,
    // return a list of dates that are still available.
    static List<LocalDate> getAvailableDates(LocalDate start, LocalDate end, List<LocalDate> booked) {

        // LocalDates are reference types so we need to consider nulls.
        if (start == null || end == null) {
            throw new IllegalArgumentException("start and end are required.");
        }

        // start must come before, or be equal to, end.
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException("start must be before or equal to end.");
        }

        ArrayList<LocalDate> result = new ArrayList<>();

        // If booked is null, assume it means there are no booked dates.
        if (booked == null) {
            booked = new ArrayList<>();
        }

        for (LocalDate current = start; current.compareTo(end) <= 0; current = current.plusDays(1)) {
            if (!booked.contains(current)) {
                result.add(current);
            }
        }

        return result;
    }
}
