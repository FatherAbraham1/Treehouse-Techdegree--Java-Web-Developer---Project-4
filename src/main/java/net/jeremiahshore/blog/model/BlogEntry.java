package net.jeremiahshore.blog.model;

import com.github.slugify.Slugify;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by Jeremiah on 7/18/2016.
 */
public class BlogEntry {
    private String slug;
    private String title;
    private String text;
    private LocalDateTime timeCreated;
    private Set<Comment> comments;

    public BlogEntry(String title, String text, LocalDateTime timeCreated) {
        this.title = title;
        this.text = text;
        this.timeCreated = timeCreated;
        this.comments = new HashSet<Comment>();
        updateSlug();
    }

    /**** GETTERS ***/

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String[] getTextAsParagraphs() {
        //TODO: (extra) use this in .hbs template to insert opening/closing tags for paragraph elements to correctly create separate paragraphs
        String[] paragraphs = text.split("\\n\\n");
        return paragraphs;
    }

    public String getSlug() {
        return slug;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    //TODO: (extra) let handlebars take care of the formatting and return the LocalDateTime in the implementation
    public String getTimeStringShort() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return timeCreated.format(formatter);
    }

    public String getTimeStringLong() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d',' yyyy 'at' HH:mm");
        return timeCreated.format(formatter);
    }

    /**** SETTERS ****/

    public void setTitle(String title) {
        this.title = title;
        updateSlug();
    }

    public void updateSlug() {
        try {
            Slugify slugify = new Slugify();
            slug = slugify.slugify(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTimeCreated(LocalDateTime timeCreated) { //used for editing? maybe remove?
        this.timeCreated = timeCreated;
    }

    public boolean addComment(Comment comment) {
        // Store these comments!
        return false;
    }

    /**** OVERRIDES ****/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogEntry blogEntry = (BlogEntry) o;

        if (slug != null ? !slug.equals(blogEntry.slug) : blogEntry.slug != null) return false;
        if (title != null ? !title.equals(blogEntry.title) : blogEntry.title != null) return false;
        if (text != null ? !text.equals(blogEntry.text) : blogEntry.text != null) return false;
        if (timeCreated != null ? !timeCreated.equals(blogEntry.timeCreated) : blogEntry.timeCreated != null)
            return false;
        return comments != null ? comments.equals(blogEntry.comments) : blogEntry.comments == null;

    }

    @Override
    public int hashCode() {
        int result = slug != null ? slug.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (timeCreated != null ? timeCreated.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }
}
