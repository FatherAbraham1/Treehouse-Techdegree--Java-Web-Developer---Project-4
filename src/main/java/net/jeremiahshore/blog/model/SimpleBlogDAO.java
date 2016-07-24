package net.jeremiahshore.blog.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jeremiah on 7/22/2016.
 */
public class SimpleBlogDAO implements BlogDAO{
    private Set<BlogEntry> blogEntries;

    public SimpleBlogDAO() {
        blogEntries = new HashSet<BlogEntry>();
    }

    @Override
    public boolean add(BlogEntry entry) {
        return blogEntries.add(entry);
    }

    @Override
    public Set<BlogEntry> findAll() {
        return new HashSet<>(blogEntries); //creates a copy so the original is not modified
    }

    @Override
    public BlogEntry findBySlug(String slug) {
        return blogEntries.stream()
                .filter(entry -> entry.getSlug().equals(slug))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
