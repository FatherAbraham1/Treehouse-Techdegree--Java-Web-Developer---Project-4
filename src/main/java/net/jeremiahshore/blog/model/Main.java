package net.jeremiahshore.blog.model;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static spark.Spark.*;

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

        /**** SPARK ROUTES ****/
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("blogEntries", dao.findAll());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
