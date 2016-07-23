package net.jeremiahshore.blog.model;

import net.jeremiahshore.blog.model.BlogEntry;

import java.util.Set;

/**
 * Created by Jeremiah on 7/18/2016.
 */
public interface BlogDAO {
    boolean add(BlogEntry entry);

    Set<BlogEntry> findAll();

    BlogEntry findBySlug(String slug);
}
