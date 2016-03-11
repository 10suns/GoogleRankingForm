/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googleranking.pages;

import googleranking.processing.GoogleData;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author tranthanhan
 */
public class Form extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Document doc;
//	try {
//
//		String google = "http://www.google.com/search?q=";
//String search = "stackoverflow";
//String charset = "UTF-8";
//String userAgent = "Mozilla"; // Change this to your company's name and bot homepage!
//
//Elements links = Jsoup.connect(google + URLEncoder.encode(search, charset)).userAgent(userAgent).get().select(".g>.r>a");
//
//for (Element link : links) {
//    String title = link.text();
//    String url = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
//    url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");
//
//    if (!url.startsWith("http")) {
//        continue; // Ads/news/etc.
//    }
//
//    System.err.println("Title: " + title);
//    System.err.println("URL: " + url);
//}
//
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
        GoogleData googleData = new GoogleData("test");
        int doc = googleData.getRankingForDomain("vvsdg");
        //response.setContentType("text/html");Lín
        //request.getRequestDispatcher("/WEB-INF/jsp/form.jsp").forward(request, response);

    }
}
