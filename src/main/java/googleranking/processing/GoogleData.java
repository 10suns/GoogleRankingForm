/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googleranking.processing;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.NameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
/**
 *
 *
 * @author tranthanhan
 */
public class GoogleData {
    private static final String GOOGLE_SEARCH_WEB = "https://www.google.com/search";
    private static final int MAX_AMT_LINK_PER_PAGE = 10;
    private static final int MAX_AMT_LINKS = 20;

    public String keyWord;

    public GoogleData(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getRankingForDomain(String Domain) {
        List<String> fiftyFirstLinks = getFiftyFirstLinks();

        return 1;
    }

    public Document getGoogleHtml(int pageNumber) {
        Document doc;
        try {
            URIBuilder builder = new URIBuilder(GOOGLE_SEARCH_WEB);
            List<NameValuePair> query = new ArrayList<NameValuePair>(2);
            query.add(new BasicNameValuePair("q", keyWord));
            query.add(new BasicNameValuePair("start", Integer.toString((pageNumber - 1) * MAX_AMT_LINK_PER_PAGE)));

            String url = builder.addParameters(query).build().toString();

            return Jsoup.connect(url).userAgent("Mozilla").get();
        } catch (IOException ex) {
            Logger.getLogger(GoogleData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(GoogleData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<String> getLinksInPage(int pageNumber) {
        Document doc = getGoogleHtml(pageNumber);
        List<String> ret = new ArrayList<String>();
        try {
            Elements links = doc.select(".g>.r>a");
            for (Element link : links) {
                String url = link.absUrl("href");
                url = URLDecoder.decode(url.substring(url.indexOf("=") +1, url.indexOf("&")), "UTF-8");
                if(url.startsWith("http") || url.startsWith("https")){
                    ret.add(url); // Ads/news/etc
                }
            }
            return ret;
        } catch (Exception e) {
            Logger.getLogger(GoogleData.class.getName()).log(Level.SEVERE, null, e);
        }
        return ret;
    }

    public List<String> getFiftyFirstLinks() {
        List<String> ret = new ArrayList<String>();
        int pageNumber = 1;
        while(ret.size() < MAX_AMT_LINKS){
            List<String> tmp = getLinksInPage(pageNumber);

            if (MAX_AMT_LINKS - ret.size() > MAX_AMT_LINK_PER_PAGE) {
                ret.addAll(tmp);
            } else {
                ret.addAll(tmp.subList(0, MAX_AMT_LINKS - ret.size()));
            }
            pageNumber++;
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        return ret;
    }
}
