package addressbook.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateHelper {

    private final static String DATE_PATTERN = "dd.MM.yyyy";

    private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static String format(LocalDate date) {
        if (date != null) {
            return DATE_FORMATTER.format(date);
        }
        return null;
    }

    public static LocalDate parse(String date) {
        try {
            return DATE_FORMATTER.parse(date, LocalDate::from);
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean validDate(String date) {
        return parse(date) != null;
    }
}
