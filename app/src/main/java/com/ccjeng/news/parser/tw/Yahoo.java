package com.ccjeng.news.parser.tw;

import android.util.Log;

import com.ccjeng.news.parser.AbstractNews;
import com.ccjeng.news.utils.Webpage;
import com.ccjeng.news.view.base.BaseApplication;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import java.io.IOException;

/**
 * Created by andycheng on 2015/11/18.
 */
public class Yahoo extends AbstractNews {
    private static final String TAG = "Yahoo";
    private String body = "";

    @Override
    public String parseHtml(final String link, String content) throws IOException {

        //replace url "news" to "mobi"
        Document doc = Jsoup.parse(content);

        String title = "";
        String time = "";//doc.select("div#image").first().text();

        try {
            title = doc.select("h1").text();

           // if (link.contains("mobi")) {
                time = doc.select("h3 time").text();
                body = doc.select("div.content-body").html();
           /* } else {
                time = doc.select("div.provider").text() + doc.select("div.publish").text();
                body = doc.select("div.bd").html();
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (BaseApplication.APPDEBUG) {
            Log.d(TAG, "title = " + title);
            Log.d(TAG, "time = " + time);
            Log.d(TAG, "body = " + body);
        }

        String b = cleaner(body);
        if (BaseApplication.APPDEBUG) {
            Log.d(TAG, "html=" + b);
        }
        return Webpage.htmlDrawer(title, time, b);

    }

    @Override
    public Boolean isEmptyContent() {
        if (body.trim().equals(""))
            return true;
        else
            return false;
    }

    protected String cleaner(String rs) {

        Whitelist wlist=new Whitelist();

        wlist.addTags("p");
        wlist.addTags("table","tbody","tr","td");
        wlist.addTags("img").addAttributes("img","src");

        return Jsoup.clean(rs,wlist);

        //return rs;

    }

}
