package net.jeremiahshore.blog.model;

import java.util.Set;

/**
 * Created by Jeremiah on 7/23/2016.
 */
public interface BlogEntryDAO {
    boolean addComment(Comment comment);

    void updateTitle(String newTitle);

    void updateText(String newText);

    Set<Comment> getComments();
}
