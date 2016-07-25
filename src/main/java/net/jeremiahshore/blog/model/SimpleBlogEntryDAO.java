package net.jeremiahshore.blog.model;

import net.jeremiahshore.blog.model.dao.BlogEntryDAO;

import java.util.Set;

/**
 * Created by Jeremiah on 7/23/2016.
 */
public class SimpleBlogEntryDAO implements BlogEntryDAO {
    private BlogEntry entry;

    public SimpleBlogEntryDAO(BlogEntry entry) {
        this.entry = entry;
    }

    @Override
    public boolean addComment(Comment comment) {
        return entry.addComment(comment);
    }

    @Override
    public void updateTitle(String newTitle) {
        entry.setTitle(newTitle);
    }

    @Override
    public void updateText(String newText) {
        entry.setText(newText);
    }

    @Override
    public Set<Comment> getComments() {
        return entry.getComments();
    }

    @Override
    public Set<Tag> getTags() {
        return entry.getTags();
    }
}
