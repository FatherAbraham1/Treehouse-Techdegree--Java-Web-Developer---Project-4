package net.jeremiahshore.blog;

import net.jeremiahshore.blog.model.*;
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
        SimpleBlogDAO blogDAO = new SimpleBlogDAO();
        Set<BlogEntry> existingEntries = BlogEntries.load();
        existingEntries.forEach(blogDAO::add);

        /**** PASSWORD & AUTHENTICATION ****/

        before((req, res) -> {
            if (req.cookie("password") != null) {
                req.attribute("password", req.cookie("password"));
            }
        });

        //pages where a password check is required
        before("/new", (req, res) -> {
            res.redirect("/password");
        });

        before("/index/:slug/edit", (req, res) -> {
            res.redirect("/password");
        });

        //password input page
        get("/password", (req,res) -> {
            if (req.attribute("password") != null
                    && req.attribute("password").equals("admin")) {
                    //TODO: redirect to the oringally requested destination
            }
            return new ModelAndView(null, "password.hbs");
        }, new HandlebarsTemplateEngine());

        //password submission
        post("/password/submit", (req, res) -> {
            String password = req.queryParams("password");
            res.cookie("password", password);
            //TODO: redirect to the oringally requested destination
            return null;
        });

        /**** INDEX ****/

        get("/", (req, res) -> {
            res.redirect("/index");
            return null;
        });

        get("/index", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("blogEntries", blogDAO.findAll());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        /**** ENTRY DETAIL ****/

        get("/index/:slug", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            BlogEntry entry = blogDAO.findBySlug(req.params("slug"));
            model.put("entry", entry);
            SimpleBlogEntryDAO entryDAO = new SimpleBlogEntryDAO(entry);
            Set<Comment> comments = entryDAO.getComments();
            model.put("comments", comments);
            return new ModelAndView(model, "entry-detail.hbs");
        }, new HandlebarsTemplateEngine());

        /**** ENTRY CREATION ****/

        get("/new", (req, res) -> {
            return new ModelAndView(null, "new-entry.hbs");
        }, new HandlebarsTemplateEngine());

        post("/publish", (req, res) -> {
            String title = req.queryParams("title");
            String text = req.queryParams("entry");
            blogDAO.add(new BlogEntry(title, text, LocalDateTime.now()));
            res.redirect("/index");
            return null;
        });

        /**** ENTRY EDITING ****/

        get("/index/:slug/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            BlogEntry entry = blogDAO.findBySlug(req.params("slug"));
            model.put("entry", entry);
            return new ModelAndView(model, "edit-entry.hbs");
        }, new HandlebarsTemplateEngine());

        post("/index/:slug/edit/save", (req, res) -> {
            String newTitle = req.queryParams("title");
            String newText = req.queryParams("entry");
            BlogEntry entry = blogDAO.findBySlug(req.params("slug"));
            BlogEntryDAO entryDAO = new SimpleBlogEntryDAO(entry);
            entryDAO.updateTitle(newTitle);
            entryDAO.updateText(newText);
            res.redirect("/index/" + entry.getSlug());
            return null;
        });

        /**** ADDING COMMENTS ****/

        post("/index/:slug/comment", (req, res) -> {
            String author = req.queryParams("name");
            String commentText = req.queryParams("comment");
            BlogEntry entry = blogDAO.findBySlug(req.params("slug"));
            BlogEntryDAO entryDAO = new SimpleBlogEntryDAO(entry);
            entryDAO.addComment(new Comment(author, commentText, LocalDateTime.now()));
            res.redirect("/index/" + entry.getSlug());
            return null;
        });

    }

}
