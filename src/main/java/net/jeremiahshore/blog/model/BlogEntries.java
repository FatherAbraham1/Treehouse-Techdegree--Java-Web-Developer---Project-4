package net.jeremiahshore.blog.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jeremiah on 7/20/2016.
 */
public class BlogEntries {
    protected static Set<BlogEntry> load() {
        Set<BlogEntry> entries = new HashSet<BlogEntry>();
        entries.add(
                new BlogEntry(
                        "The best day I’ve ever had",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut rhoncus felis, vel tincidunt " +
                                "neque. Vestibulum ut metus eleifend, malesuada nisl at, scelerisque sapien. Vivamus " +
                                "pharetra massa libero, sed feugiat turpis efficitur at.\n\n" +
                                "Cras egestas ac ipsum in posuere. Fusce suscipit, libero id malesuada placerat, orci " +
                                "velit semper metus, quis pulvinar sem nunc vel augue. In ornare tempor metus, sit " +
                                "amet congue justo porta et. Etiam pretium, sapien non fermentum consequat, dolor " +
                                "augue gravida lacus, non accumsan lorem odio id risus. Vestibulum pharetra tempor " +
                                "molestie. Integer sollicitudin ante ipsum, a luctus nisi egestas eu. Cras accumsan " +
                                "cursus ante, non dapibus tempor.",
                        //TODO: pickup from here, adding a date/time, refer to java.time in the javadocs
                        //TODO: add content for the other example entries once the date/time issue is sorted out

        )

        );
    }
}
