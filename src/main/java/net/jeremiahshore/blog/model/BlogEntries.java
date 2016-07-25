package net.jeremiahshore.blog.model;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jeremiah on 7/20/2016.
 */
public class BlogEntries {
    public static Set<BlogEntry> load() {
        Set<BlogEntry> entries = new HashSet<BlogEntry>();

        BlogEntry entryWithComment = new BlogEntry(
                "An Encounter with Hipsters",
                "Mumblecore cornhole vegan, sartorial whatever mlkshk cold-pressed tilde listicle ennui pickled " +
                        "pop-up retro pitchfork. Semiotics gentrify chia, synth kickstarter ugh helvetica ethical " +
                        "cold-pressed schlitz echo park umami. Ugh bitters hella, blue bottle hashtag ennui gochujang " +
                        "narwhal post-ironic cray artisan lumbersexual scenester four loko put a bird on it. Actually " +
                        "selfies paleo vice, poutine gentrify man braid YOLO small batch neutra keytar food truck " +
                        "hoodie sartorial. Hoodie roof party godard, man braid shoreditch cornhole direct trade " +
                        "mixtape affogato paleo retro truffaut. Banjo single-origin coffee dreamcatcher, bespoke " +
                        "raw denim mumblecore lomo kickstarter chambray waistcoat XOXO williamsburg intelligentsia. " +
                        "Flexitarian pitchfork kickstarter narwhal before they sold out retro ramps letterpress austin " +
                        "locavore.",
                LocalDateTime.of(2016, Month.MARCH, 14, 17, 34));
        entryWithComment.addComment(new Comment("Jeremiah", "Actually, that's not irony...", LocalDateTime.now()));
        entryWithComment.addTag("ironic");
        entryWithComment.addTag("sartorial");
        entryWithComment.addTag("man bun");
        entries.add(entryWithComment);

        BlogEntry entryWithTwoComments = new BlogEntry(
                "Better with Bacon",
                "Bacon ipsum dolor amet cupim hamburger venison, sausage rump short ribs flank kevin " +
                        "pancetta bacon pig ball tip bresaola turkey pork loin. Pastrami andouille prosciutto " +
                        "landjaeger corned beef beef flank brisket tenderloin leberkas alcatra strip steak " +
                        "cupim. Pastrami kielbasa sirloin landjaeger rump, shankle pork chop flank capicola " +
                        "kevin beef andouille pork. Doner jerky turkey capicola short ribs alcatra.",
                LocalDateTime.of(2016, Month.JANUARY, 5, 12, 10));
        entryWithTwoComments.addComment(new Comment("Craig", "Haha, that sounds awesome", LocalDateTime.of(2016, Month.JANUARY, 10, 5, 0)));
        entryWithTwoComments.addComment(new Comment("Rama", "I don't know about all of that...", LocalDateTime.of(2016, Month.JANUARY, 12, 16, 0)));
        entryWithTwoComments.addTag("meaty");
        entries.add(entryWithTwoComments);

        entries.add(
                new BlogEntry(
                        "Zombies in the Candy Kingdom",
                        "Come on, come on... Work. Algebraic! Wait, somethings wrong. SUGAR! Ew! Hey look, the " +
                                "decorpsinator serum is working! No, this is wrong. Theyre not coming back to life " +
                                "theyre still dead! The decorpsinator serum its incomplete! Must eat sugar! Youre " +
                                "grounded, Mister. Oh, this is really bad. They're going to be attracted to the Candy " +
                                "Kingdom! Why? Because the Candy People are made of sugar, ya ding dong!",
                        LocalDateTime.of(2016, Month.FEBRUARY, 22, 9, 18)
                ));

        return entries;
    }
}
