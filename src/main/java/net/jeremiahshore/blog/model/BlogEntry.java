package net.jeremiahshore.blog.model;

import com.github.slugify.Slugify;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jeremiah on 7/18/2016.
 */
public class BlogEntry {
    private String slug;
    private String title;
    private String text;
    private Date timeCreated;
    private Set<Comment> comments;

    public BlogEntry(String title, String text, Date timeCreated) {
        this.title = title;
        this.text = text;
        this.timeCreated = timeCreated;
        this.comments = new HashSet<Comment>();
        try {
            Slugify slugify = new Slugify();
            slug = slugify.slugify(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**** GETTERS ***/
    public String getTitle() {
        return title;
    }

    public String getText() {
        //TODO: detect double carriage returns and insert opening/closing tags for paragraph elements
        return text;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    /**** SETTERS ****/
    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public boolean addComment(Comment comment) {
        // Store these comments!
        return false;
    }

    /**** OVERRIDDEN METHODS ****/
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
