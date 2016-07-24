package net.jeremiahshore.blog.model;

import java.time.LocalDateTime;

/**
 * Created by Jeremiah on 7/18/2016.
 */
public class Comment {
    private String author;
    private String text;
    private LocalDateTime dateTimeCreated;

    public Comment(String author, String text, LocalDateTime dateTimeCreated) {
        this.author = author;
        this.text = text;
        this.dateTimeCreated = dateTimeCreated;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public String getTimeStringShort() {
        return TimeFormatHelper.shortFormat(dateTimeCreated);
    }

    public String getTimeStringLong() {
        return TimeFormatHelper.longFormat(dateTimeCreated);
    }
}