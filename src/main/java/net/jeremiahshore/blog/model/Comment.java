package net.jeremiahshore.blog.model;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Jeremiah on 7/18/2016.
 */
public class Comment {
    private String author;
    private String text;
    private LocalDateTime timeCreated;

    public Comment(String author, String text, LocalDateTime timeCreated) {
        this.author = author;
        this.text = text;
        this.timeCreated = timeCreated;
    }

    public Comment(String author, String text) {
        new Comment(author, text, LocalDateTime.now());
    }


}