package net.jeremiahshore.blog.model;

import com.github.slugify.Slugify;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Jeremiah on 7/18/2016.
 */
public class BlogEntry {
    private String slug;
    private String title;
    private String text;
    private LocalDateTime dateTimeCreated;
    private Set<Comment> comments;
    private Set<Tag> tags;

    public BlogEntry(String title, String text, LocalDateTime dateTimeCreated) {
        this.title = title;
        this.text = text;
        this.dateTimeCreated = dateTimeCreated;
        this.comments = new HashSet<>();
        this.tags = new HashSet<>();
        updateSlug();
    }

    /**** GETTERS ***/

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getSlug() {
        return slug;
    }

    public String getTimeStringShort() {
        return TimeFormatHelper.shortFormat(dateTimeCreated);
    }

    public String getTimeStringLong() {
        return TimeFormatHelper.longFormat(dateTimeCreated);
    }

    protected Set<Tag> getTags() {
        return tags;
    }

    protected Set<Comment> getComments() {
        return comments;
    }

    /**** SETTERS ****/

    protected void setTitle(String title) {
        this.title = title;
        updateSlug();
    }

    protected void setText(String text) {
        this.text = text;
    }

    protected boolean addComment(Comment comment) {
        return comments.add(comment);
    }

    protected boolean addTag(String tag) {
        return tags.add(new Tag(tag));
    }

    /**** HELPERS ****/

    public void updateSlug() {
        try {
            Slugify slugify = new Slugify();
            slug = slugify.slugify(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        if (dateTimeCreated != null ? !dateTimeCreated.equals(blogEntry.dateTimeCreated) : blogEntry.dateTimeCreated != null)
            return false;
        if (comments != null ? !comments.equals(blogEntry.comments) : blogEntry.comments != null) return false;
        return tags != null ? tags.equals(blogEntry.tags) : blogEntry.tags == null;

    }

    @Override
    public int hashCode() {
        int result = dateTimeCreated != null ? dateTimeCreated.hashCode() : 0;
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
        //TODO: add a unique ID to this class, use when loading entries at startup
        //the hashcode as it was previously with title, text, comments was not allowing removal from the "model" because the returned hashcode had changed
    }
}
