package net.jeremiahshore.blog.model;

/**
 * Created by Jeremiah on 7/24/2016.
 */
public class Tag {
    private String text;

    public Tag(String tag) {
        text = tag;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
