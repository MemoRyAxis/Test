package test.test.test.test.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MrSpider {

    public static void main(String[] args) throws Exception {
        Document playListDoc = Jsoup
                .connect("http://music.163.com/discover/playlist/?cat=摇滚")
//                .data("cat", "%E6%B0%91%E8%B0%A3")
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:41.0) Gecko/20100101 Firefox/41.0")
                .timeout(3000)
                .get();
        
        System.out.println(playListDoc.title());

        Elements playListLinks = playListDoc.getElementsByTag("body").get(0).getElementsByClass("f-fr");
        System.out.println(playListLinks.size());
        
        for (int i = 0; i < 3; i++) { //playListLinks.size(); i++) { 
            Element ffr = playListLinks.get(i);
            if (NiceUtil.isEmptyString(ffr.attr("data-res-id"))) {
                continue;
            }
            
            String playList = playListLinks.get(i).attr("data-res-id").toString();
            System.out.println("play list id: " + playList);
            Document musicDoc = Jsoup
                    .connect("http://music.163.com/playlist?id=" + playList)
                    .get();
            
//            System.out.println(musicDoc);
            
            Elements musicListLinks = musicDoc.getElementsByTag("body").get(0).getElementsByClass("f-hide");            
            musicListLinks = musicListLinks.get(musicListLinks.size() - 1).getElementsByTag("a");
            for (int j = 0; j < musicListLinks.size(); j ++) {
                Element musicLink = musicListLinks.get(j);
                String musicId = musicLink.attr("href").replace("/song?id=", "");
                System.out.println("\t music id: " + musicId);
            }
            System.out.println();
        }
        

        // NiceUtil.dissect(doc.getElementsByTag("body").get(0));


        // System.out.println(doc.getElementById("m-disc-pl-c"));


        // NiceUtil.dissect(doc);
        // System.out.println(doc.getElementsByClass("dec").size());
    }
}
