package net.jeremiahshore.blog.model;

import java.util.HashSet;
import java.util.Set;

import static spark.Spark.staticFileLocation;

/**
 * Created by Jeremiah on 7/22/2016.
 */
public class Main {
    public static void main(String[] args) {
        staticFileLocation("/public");

        //load some test blog entries
        SimpleBlogDAO dao = new SimpleBlogDAO();
        Set<BlogEntry> existingEntries = BlogEntries.load();
        existingEntries.forEach(dao::add);
    }
}
