package test.test.test.test.test;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import com.sun.syndication.io.XmlReader;

public class MrRss {

    public static void main(String[] args) throws Exception {
        
        boolean ok = false;
        if (args.length==2) {
            try {
                String outputType = args[0];

                URL feedUrl = new URL(args[1]);

                SyndFeedInput input = new SyndFeedInput();
                SyndFeed feed = input.build(new XmlReader(feedUrl));
                feed.setFeedType(outputType);
                SyndFeedOutput output = new SyndFeedOutput();
                output.output(feed,new PrintWriter(System.out));

                ok = true;
            }
            catch (Exception ex) {
                System.out.println("ERROR: "+ex.getMessage());
            }
        }

        if (!ok) {
            System.out.println();
            System.out.println("FeedConverter converts between syndication feeds types.");
            System.out.println("The first parameter must be the feed type to convert to.");
            System.out.println(" [valid values are: rss_0.9, rss_0.91, rss_0.92, rss_0.93, ]");
            System.out.println(" [                  rss_0.94, rss_1.0, rss_2.0 & atom_0.3  ]");
            System.out.println("The second parameter must be the URL of the feed to convert.");
            System.out.println();
        }

        URL feedUrl = new URL("http://feed.feedsky.com/qiushi");
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new InputStreamReader(feedUrl.openStream()));
        SyndFeedOutput output = new SyndFeedOutput();
        output.output(feed,new PrintWriter(System.out));
        
    }
}
