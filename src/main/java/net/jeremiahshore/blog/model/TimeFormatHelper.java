package net.jeremiahshore.blog.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Jeremiah on 7/23/2016.
 */
public class TimeFormatHelper {
    public static String shortFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public static String longFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d',' yyyy 'at' HH:mm"));
    }
}
