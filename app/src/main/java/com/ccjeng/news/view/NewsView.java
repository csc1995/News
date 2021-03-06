package com.ccjeng.news.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ccjeng.news.R;
import com.ccjeng.news.parser.AbstractNews;
import com.ccjeng.news.presenter.NewsViewPresenter;
import com.ccjeng.news.presenter.NewsViewView;
import com.ccjeng.news.utils.Analytics;
import com.ccjeng.news.utils.Category;
import com.ccjeng.news.utils.Network;
import com.ccjeng.news.utils.PreferenceSetting;
import com.ccjeng.news.utils.UI;
import com.ccjeng.news.utils.Webpage;
import com.ccjeng.news.view.base.BaseApplication;
import com.ccjeng.news.view.base.MVPBaseActivity;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

//todo 加上新聞小幫手檢核 sample: https://github.com/g0v/newshelper-extension/blob/master/background.js

public class NewsView extends MVPBaseActivity<NewsViewView, NewsViewPresenter> implements NewsViewView {

    private static final String TAG = NewsView.class.getSimpleName();

    WebView webView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progress_wheel)
    ProgressWheel progressWheel;
    @BindView(R.id.main)
    NestedScrollView main;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;

    private Analytics ga;

    private String newsName;
    private String newsUrl;
    private String newsTitle;
    //private MoPubView moPubView;
    private int sourceNumber;
    private String tabName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);

        ga = new Analytics();
        ga.trackerPage(this);

        webView = new WebView(getApplicationContext());
        main.addView(webView);

        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        PreferenceSetting.getPreference(this);

        Bundle bundle = this.getIntent().getExtras();
        sourceNumber = Integer.parseInt(bundle.getString("SourceNum"));
        tabName = bundle.getString("SourceTab");
        newsName = bundle.getString("NewsName");
        newsUrl = bundle.getString("newsUrl");
        newsTitle = bundle.getString("newsTitle");

        getSupportActionBar().setTitle(newsTitle);
        getSupportActionBar().setSubtitle(newsName);

        main.setBackgroundColor(Color.parseColor(BaseApplication.getPrefBGColor()));

        //moPubView = (MoPubView) findViewById(R.id.adview);

        if (Network.isNetworkConnected(this)) {

            progressWheel.setVisibility(View.VISIBLE);
            main.setVisibility(View.GONE);
            mPresenter.getNews(tabName, sourceNumber, newsUrl);

            //Network.AdView(moPubView, Constant.AD_MoPub_View);

        } else {
            this.showError(R.string.network_error);
        }

    }
    @Override
    protected void onStart() {
        super.onStart();
        PreferenceSetting.getPreference(this);
    }

    @Override
    public void showError(int message) {
        UI.showErrorSnackBar(coordinator, message);
    }

    @Override
    protected void onDestroy() {
        //if (moPubView != null) {
        //    moPubView.destroy();
        //}

        super.onDestroy();
        mPresenter.onDestroy();
        if (webView != null) {
            webView.setVisibility(View.GONE);
            webView.removeAllViews();
            webView.destroy();
            //webView = null;
        }
    }

    @Override
    protected NewsViewPresenter createPresenter() {
        return new NewsViewPresenter(this, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web, menu);

        MenuItem menuItem1 = menu.findItem(R.id.action_browser);
        menuItem1.setIcon(new IconicsDrawable(this, CommunityMaterial.Icon.cmd_web).actionBar().color(Color.WHITE));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_browser:
                mPresenter.menuOpenBrowser(newsUrl);
                break;
            case R.id.action_share:
                mPresenter.menuShare("["+ newsName + "] " + newsTitle + " " + newsUrl);
                break;
            case R.id.action_setting:
                startActivity(new Intent(NewsView.this, Preference.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void drawHtmlPage(String html) {

        if (webView != null) {
            webView.getSettings().setJavaScriptEnabled(true);
            //context.webView.getSettings().setSupportZoom(true);
            //context.webView.getSettings().setBuiltInZoomControls(true);
            //context.webView.getSettings().setCacheMode(2); //LOAD_NO_CACHE
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

            //context.webView.getSettings().setLoadWithOverviewMode(true);
            //context.webView.getSettings().setUseWideViewPort(true);
        }

        Category cat = new Category(NewsView.this);
        AbstractNews parser = cat.getNewsParser(tabName, sourceNumber);

        try {

            String newsContent = parser.parseHtml(newsUrl, html);

            progressWheel.setVisibility(View.GONE);
            main.setVisibility(View.VISIBLE);

            if (parser.isEmptyContent()) {
                //if parse result is empty, then show webview directly..
                Analytics ga = new Analytics();
                ga.trackEvent(NewsView.this, "Error", "Empty Content", newsUrl, 0);

                this.showError(R.string.parsing_error_transfer);

                webView.loadUrl(newsUrl);

                webView.setWebViewClient(new WebViewClient() {

                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        super.onPageStarted(view, url, favicon);
                        progressWheel.setVisibility(View.VISIBLE);
                        main.setVisibility(View.GONE);
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        progressWheel.setVisibility(View.GONE);
                        main.setVisibility(View.VISIBLE);
                    }
                });
                webView.setWebChromeClient(new WebChromeClient() {

                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                        super.onProgressChanged(view, newProgress);
                        progressWheel.setProgress((float) newProgress / 100);

                        if (newProgress > 90) {
                            progressWheel.setVisibility(View.GONE);
                            main.setVisibility(View.VISIBLE);
                        }
                    }
                });

            } else {

                if (webView != null) {
                    webView.loadDataWithBaseURL(null, newsContent, "text/html", "utf-8", "about:blank");

                    //image fit screen

                    final String js;
                    js = "javascript:(function () { " +
                            " var w = " + Webpage.getWidth(NewsView.this) + ";" +
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
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
