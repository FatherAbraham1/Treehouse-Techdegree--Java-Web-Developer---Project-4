package net.jeremiahshore.blog;

import net.jeremiahshore.blog.model.*;
import net.jeremiahshore.blog.model.dao.BlogEntryDAO;
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
            //persistent password cookie
            if (req.cookie("password") != null) {
                req.attribute("password", req.cookie("password"));
            }
            //persistent destination cookie
            if (req.cookie("destination") != null) {
                req.attribute("destination", req.cookie("destination"));
            }
        });

        //pages where a password check is required
        before("/new", (req, res) -> {
            res.cookie("destination", req.pathInfo());
            if (req.attribute("password") == null || !req.attribute("password").equals("admin")) {
                res.redirect("/password");
            }
        });

        before("/index/:slug/edit", (req, res) -> {
            res.cookie("destination", req.pathInfo());
            //TODO: (extra) allow redirect to original destination (works on /new)
            if (req.attribute("password") == null || !req.attribute("password").equals("admin")) {
                res.redirect("/password");
            }
        });

        //password input page
        get("/password", (req,res) -> {
            return new ModelAndView(null, "password.hbs");
        }, new HandlebarsTemplateEngine());

        //password submission
        post("/sign-in", (req, res) -> {
            String password = req.queryParams("password");
            res.cookie("password", password);
            if (req.attribute("destination") != null) {
                res.redirect(req.attribute("destination"));
            } else {
                res.redirect("/");
            }
            return null;
        });

        /**** INDEX ****/

        get("/", (req, res) -> {
            res.redirect("/index");
            return null;
        });

        //"home" page
        get("/index", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("blogEntries", blogDAO.findAll());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        /**** ENTRIES ****/

        //detail page
        get("/index/:slug", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            BlogEntry entry = blogDAO.findBySlug(req.params("slug"));
            model.put("entry", entry);
            SimpleBlogEntryDAO entryDAO = new SimpleBlogEntryDAO(entry);
            Set<Comment> comments = entryDAO.getComments();
            model.put("comments", comments);
            //TODO: (extra) add ability for admin user to add or remove tags on new or existing entries
            Set<Tag> tags = entryDAO.getTags();
            model.put("tags", tags);
            return new ModelAndView(model, "entry-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //create new entry
        get("/new", (req, res) -> {
            return new ModelAndView(null, "new-entry.hbs");
        }, new HandlebarsTemplateEngine());

        //submit new entry
        post("/publish", (req, res) -> {
            String title = req.queryParams("title");
            String text = req.queryParams("entry");
            blogDAO.add(new BlogEntry(title, text, LocalDateTime.now()));
            res.redirect("/index");
            return null;
        });

        /**** ENTRY CHANGES ****/

        //edit entry page
        get("/index/:slug/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            BlogEntry entry = blogDAO.findBySlug(req.params("slug"));
            model.put("entry", entry);
            return new ModelAndView(model, "edit-entry.hbs");
        }, new HandlebarsTemplateEngine());

        //save changes after editing
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

        //delete the page
        get("/index/:slug/delete", (req, res) -> {
            BlogEntry entry = blogDAO.findBySlug(req.params("slug"));
            boolean result = blogDAO.remove(entry);
            System.out.println(result);
            //TODO: resolve bug where entries are mysteriously not deleted once a new comment has been added
            //TODO: (extra) display some sort of warning or confirmation dialog before deleting
            res.redirect("/");
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

        /**** EXCEPTIONS ****/

        exception(NotFoundException.class, (exc, req, res) -> {
            res.status(404);
            HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
            String html = engine.render(new ModelAndView(null, "not-found.hbs"));
            res.body(html);
        });
    }

}
