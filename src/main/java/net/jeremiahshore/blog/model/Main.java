package net.jeremiahshore.blog.model;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.time.LocalDateTime;
import java.util.HashMap;
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
            res.redirect("/index");
            return null;
        });

        get("/index", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("blogEntries", dao.findAll());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/index/:slug", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            BlogEntry entry = dao.findBySlug(req.params("slug"));
            model.put("entry", entry);
            return new ModelAndView(model, "entry-detail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/new", (req, res) -> {
            return new ModelAndView(null, "new-entry.hbs");
        }, new HandlebarsTemplateEngine());

        post("/publish", (req, res) -> {
            String title = req.queryParams("title");
            String text = req.queryParams("entry");
            dao.add(new BlogEntry(title, text, LocalDateTime.now()));
            res.redirect("/index");
            return null;
        });

        get("/index/:slug/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            BlogEntry entry = dao.findBySlug(req.params("slug"));
            model.put("entry", entry);
            return new ModelAndView(model, "edit-entry.hbs");
        }, new HandlebarsTemplateEngine());

        post("/index/:slug/edit/save", (req, res) -> {
            String newTitle = req.queryParams("title");
            String newText = req.queryParams("entry");
            BlogEntry entry = dao.findBySlug(req.params("slug"));
            entry.setTitle(newTitle); //TODO: create a DAO for modifying blog entries, this is also reqd for adding comments
            entry.setText(newText);
            res.redirect("/index/" + entry.getSlug());
            return null;
        });

    }
}
