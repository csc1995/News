package com.ccjeng.news.controler.web;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ccjeng.news.R;
import com.ccjeng.news.parser.INewsParser;
import com.ccjeng.news.parser.tw.AppleDaily;
import com.ccjeng.news.view.NewsView;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.io.IOException;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by andycheng on 2015/11/15.
 */
public class NewsHandler {

    private static final String TAG = "NewsContent";


    public static void getNewsContent(final NewsView context, String url, String tab, int position) {

        final String mimeType = "text/html";
        final String encoding = "utf-8";

        RequestQueue mRequestQueue = Volley.newRequestQueue(context);
        Log.d(TAG, "url = " + url);

        final ProgressWheel progressWheel = (ProgressWheel) context.findViewById(R.id.progress_wheel);
        final WebView webView = (WebView) context.findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);

        progressWheel.setVisibility(View.VISIBLE);
        webView.setVisibility(View.GONE);

        StringRequest req = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressWheel.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);

                INewsParser parser = new AppleDaily();

                try {
                    String newsContent = parser.parseHtml(response);
                    webView.loadDataWithBaseURL(null, newsContent, mimeType, encoding, "about:blank");


                    //image fit screen
                    /*
                    final String js;
                    js= "javascript:(function () { " +
                            " var w = " + getWidth(context)/2 + ";" +
                            " for( var i = 0; i < document.images.length; i++ ) {" +
                            " var img = document.images[i]; " +
                            "   img.height = Math.round( img.height * ( w/img.width ) ); " +
                            "   img.width = w; " +
                            " }" +
                            " })();";

                    webView.setWebViewClient(new WebViewClient() {
                        @Override
                        public void onPageFinished(WebView view, String url) {
                            webView.loadUrl(js);
                        }
                    });
*/

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.getMessage());

                Crouton.makeText(context, R.string.data_error, Style.ALERT,
                        (ViewGroup) context.findViewById(R.id.main)).show();
            }
        });

        mRequestQueue.add(req);
    }

    private static int getWidth(NewsView context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Configuration config = context.getResources().getConfiguration();
        int vWidth = 0;
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE)
            return dm.heightPixels;
        else
            return dm.widthPixels;
    }
}