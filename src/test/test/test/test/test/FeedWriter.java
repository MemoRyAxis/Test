package test.test.test.test.test;

import java.net.URL;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class FeedWriter {

    public static void main(String[] args) throws Exception {
//        URL url = new URL("http://www.williamlong.info/rss.xml"); // 月光 - -
//        URL url = new URL("http://www.nbweekly.com/rss/smw/"); // 阅读 南都周刊 50
         URL url = new URL("http://www.u148.net/rss/"); // 图片 搞笑 20
//         URL url = new URL("http://hanhanone.sinaapp.com/feed/dajia"); // 新闻 30
//         URL url = new URL("http://www.read.org.cn/feed"); // 学习方法 时间管理 5
//         URL url = new URL("http://9.douban.com/rss/culture"); // 文化 15
//         URL url = new URL("http://blog.sina.com.cn/rss/1691761292.xml"); // 有趣 Blog /10
        
         

        XmlReader reader = null;

        try {

            reader = new XmlReader(url);
            SyndFeed feed = new SyndFeedInput().build(reader);

            // System.out.println("Feed Title: " + feed.getAuthor());
            // System.out.println("Feed Desc: " + feed.getDescription());

            // System.out.println(feed.getEntries());
            // List list = feed.getEntries();
            // for (int i = 0; i < list.size(); i++) {
            // NiceUtil.dissect(list.get(i));
            // }

            // NiceUtil.dissect(feed);
            System.out.println(feed.getTitle());
            System.out.println(feed.getLink());
            System.out.println(feed.getEntries().size());


            
            // will be null
            
//            System.out.println(feed);
            
//            System.out.println(((SyndEntryImpl) (feed.getEntries().get(2))).getDescription()
//                    .getValue());
//
            NiceUtil.dissect(((SyndEntryImpl) (feed.getEntries().get(2))).getDescription());


            // for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
            // SyndEntry entry = (SyndEntry) i.next();
            // System.out.println(entry.getTitle());
            // }
        } finally {
            if (reader != null) reader.close();
        }
    }
}
