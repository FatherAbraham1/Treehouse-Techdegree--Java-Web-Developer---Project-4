package net.jeremiahshore.blog.model;

import java.time.LocalDateTime;
import java.time.Month;
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
                        LocalDateTime.of(2016, Month.JANUARY, 1, 1, 0)
                )
        );
        entries.add(
                new BlogEntry(
                        "The absolute worst day I’ve ever had",
                        "Bacon ipsum dolor amet cupim hamburger venison, sausage rump short ribs flank kevin " +
                                "pancetta bacon pig ball tip bresaola turkey pork loin. Pastrami andouille prosciutto " +
                                "landjaeger corned beef beef flank brisket tenderloin leberkas alcatra strip steak " +
                                "cupim. Pastrami kielbasa sirloin landjaeger rump, shankle pork chop flank capicola " +
                                "kevin beef andouille pork. Doner jerky turkey capicola short ribs alcatra.",
                        LocalDateTime.of(2016, Month.JANUARY, 1, 1, 0)
                )
        );
        entries.add(
                new BlogEntry(
                        "That time at the mall",
                        "Tri-tip ground round drumstick, short loin pig shoulder chicken beef beef ribs boudin t-bone " +
                                "andouille. Pastrami beef boudin sirloin, short loin swine turducken capicola turkey. " +
                                "Pastrami chuck beef ribs, alcatra pancetta meatloaf cow jowl prosciutto spare ribs " +
                                "turducken tri-tip shankle. Alcatra meatloaf meatball landjaeger boudin shoulder. " +
                                "Chicken short loin kielbasa tail, corned beef filet mignon tongue bacon venison " +
                                "shank strip steak. Kevin shankle brisket pork sirloin venison biltong pig pancetta " +
                                "shank turducken andouille hamburger tri-tip. Tail pork belly boudin, strip steak " +
                                "prosciutto beef flank hamburger salami pork chop capicola frankfurter.\n\n" +
                                "Pancetta hamburger chuck, kielbasa pork belly pastrami capicola. Spare ribs pastrami " +
                                "sausage, pork belly turkey alcatra rump strip steak filet mignon. Swine bresaola " +
                                "rump salami. Pig chicken ham hock ball tip spare ribs pork shoulder salami filet " +
                                "mignon prosciutto strip steak. Tri-tip bacon corned beef cupim short ribs ham, pork " +
                                "short loin pastrami drumstick. Sirloin short loin pork chop pancetta, andouille " +
                                "kielbasa porchetta prosciutto picanha turducken leberkas shankle cow drumstick " +
                                "sausage. Frankfurter cow meatball, brisket pastrami turducken fatback t-bone sausage " +
                                "jerky short loin tongue swine porchetta.",
                        LocalDateTime.of(2016, Month.JANUARY, 1, 1, 0)
                )
        );
        entries.add(
                new BlogEntry(
                        "Dude, where’s my car?",
                        "Jowl cow drumstick pancetta, beef corned beef brisket. T-bone biltong boudin sirloin ham " +
                                "flank drumstick kevin rump ground round turducken tail. Pastrami rump meatball, " +
                                "biltong tenderloin bresaola venison fatback filet mignon ribeye. Pork belly ground " +
                                "round spare ribs turducken hamburger chicken ham. Porchetta leberkas venison " +
                                "andouille rump ham. Pig venison swine flank turkey pork belly, fatback pancetta. " +
                                "Picanha doner ham pork belly pork loin capicola.\n\n" +
                                "Rump tail corned beef doner, pancetta t-bone chicken shank brisket venison pork " +
                                "belly tenderloin cow. Short loin jowl meatloaf turducken flank swine sausage bresaola " +
                                "pork loin. Swine andouille capicola leberkas venison prosciutto doner boudin pork " +
                                "loin chicken tail jerky. Spare ribs pastrami sausage swine jowl frankfurter sirloin. " +
                                "Ham andouille turducken tail chicken brisket. Tri-tip prosciutto alcatra salami " +
                                "shoulder doner kevin strip steak pork chuck, ball tip boudin pork belly turkey. " +
                                "Beef meatball leberkas strip steak.",
                        LocalDateTime.of(2016, Month.JANUARY, 1, 1, 0)
                )
        );
        return entries;
    }
}
