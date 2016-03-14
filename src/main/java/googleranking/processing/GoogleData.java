/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googleranking.processing;

import java.io.IOException;
import java.net.URI;
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
    private static final String MIDDLE_SERVER = "http://xymuqmc0.iqservs.jp/cgi-bin/getHTML.cgi";
    private static final String GOOGLE_SEARCH_WEB = "http://www.google.co.jp/search";

    public String keyWord;

    public GoogleData(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getRankingForDomain(String Domain) {
        List<String> links = getLinksInPage();
        return links.indexOf(Domain) + 1;
    }

    public Document getGoogleHtml() {
        Document doc;
        try {
            URIBuilder search_builder = new URIBuilder(GOOGLE_SEARCH_WEB);
            List<NameValuePair> search_query = new ArrayList<NameValuePair>(2);
            search_query.add(new BasicNameValuePair("pws", "0"));
            search_query.add(new BasicNameValuePair("filter", "1"));
            search_query.add(new BasicNameValuePair("hl", "jp"));
            search_query.add(new BasicNameValuePair("num", Integer.toString(50)));
            search_query.add(new BasicNameValuePair("q", keyWord));
            search_query.add(new BasicNameValuePair("start", "0"));

            String search_url = search_builder.addParameters(search_query).build().toString();

            URIBuilder middle_builder = new URIBuilder(MIDDLE_SERVER);
            List<NameValuePair> middle_query = new ArrayList<NameValuePair>(2);
            middle_query.add(new BasicNameValuePair("url", search_url));
            middle_query.add(new BasicNameValuePair("ip", "49.212.93.235"));

            String url = middle_builder.addParameters(middle_query).build().toString();

            return Jsoup.connect(url).userAgent("Mozilla").get();
        } catch (IOException ex) {
            Logger.getLogger(GoogleData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(GoogleData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<String> getLinksInPage() {
        Document doc = getGoogleHtml();
        List<String> ret = new ArrayList<String>();
        try {
            Elements links = doc.select(".g>.r>a");
            for (Element link : links) {
                String url = link.absUrl("href");
                url = URLDecoder.decode(url.substring(url.indexOf("=") +1, url.indexOf("&")), "UTF-8");
                if(url.startsWith("http") || url.startsWith("https")){
                    ret.add(getDomain(url)); // Ads/news/etc
                }
            }
        } catch (Exception e) {
            Logger.getLogger(GoogleData.class.getName()).log(Level.SEVERE, null, e);
        }
        return ret;
    }

    private String getDomain(String url) throws URISyntaxException{
        URI uri = new URI(url);
        String domain = uri.getHost();
        return domain;
    }
}
