package com.ccjeng.news.utils;

import android.content.Context;

import com.ccjeng.news.R;
import com.ccjeng.news.parser.AbstractNews;
import com.ccjeng.news.parser.hk.AM730;
import com.ccjeng.news.parser.hk.ETNet;
import com.ccjeng.news.parser.hk.HKAppleDaily;
import com.ccjeng.news.parser.hk.HKEJ;
import com.ccjeng.news.parser.hk.HKHeadline;
import com.ccjeng.news.parser.hk.InMediaHK;
import com.ccjeng.news.parser.hk.OrientalDaily;
import com.ccjeng.news.parser.hk.RTHK;
import com.ccjeng.news.parser.hk.TheStandNews;
import com.ccjeng.news.parser.sg.Daliulian;
import com.ccjeng.news.parser.sg.Guangming;
import com.ccjeng.news.parser.sg.Kwongwah;
import com.ccjeng.news.parser.sg.Zaobao;
import com.ccjeng.news.parser.tw.AppleDaily;
import com.ccjeng.news.parser.tw.CNA;
import com.ccjeng.news.parser.tw.CNYes;
import com.ccjeng.news.parser.tw.ChinaTimes;
import com.ccjeng.news.parser.tw.ETToday;
import com.ccjeng.news.parser.tw.LibertyTimes;
import com.ccjeng.news.parser.tw.NewTalk;
import com.ccjeng.news.parser.tw.Storm;
import com.ccjeng.news.parser.tw.TheNewsLens;
import com.ccjeng.news.parser.tw.UDN;
import com.ccjeng.news.parser.tw.Yahoo;

/**
 * Created by andycheng on 2015/11/14.
 */
public class Category {

    private Context context;

    public Category(Context context) {
        this.context = context;
    }

    public String[] getCategory(String tab, int position) {
        String[] category = null;

        if (tab.equals("TW")) {
            switch (position) {
                case 0:
                    category = context.getResources().getStringArray(R.array.newscatsCNA);
                    break;
                case 1:
                    category = context.getResources().getStringArray(R.array.newscatsYahoo);
                    break;
                case 2:
                    category = context.getResources().getStringArray(R.array.newscatsUDN);
                    break;
                case 3:
                    category = context.getResources().getStringArray(R.array.newscatsUDNFN);
                    break;
                case 4:
                    category = context.getResources().getStringArray(R.array.newscatsChinaTimes);
                    break;
                case 5:
                    category = context.getResources().getStringArray(R.array.newscatsCommercial);
                    break;
                case 6:
                    category = context.getResources().getStringArray(R.array.newscatsWant);
                    break;
                case 7:
                    category = context.getResources().getStringArray(R.array.newscatsStorm);
                    break;
                case 8:
                    category = context.getResources().getStringArray(R.array.newscatsEttoday);
                    break;
                case 9:
                    category = context.getResources().getStringArray(R.array.newscatsCNYes);
                    break;
                case 10:
                    category = context.getResources().getStringArray(R.array.newscatsNewsTalk);
                    break;
                case 11:
                    category = context.getResources().getStringArray(R.array.newscatsLibertyTimes);
                    break;
                case 12:
                    category = context.getResources().getStringArray(R.array.newscatsAppDaily);
                    break;
                case 13:
                    category = context.getResources().getStringArray(R.array.newscatsAppDailyRT);
                    break;
                case 14:
                    category = context.getResources().getStringArray(R.array.newsCatsTheNewsLens);
                    break;
            }
        } else if (tab.equals("HK")) {
            switch (position) {
                case 0:
                    category = context.getResources().getStringArray(R.array.newscatsHKAppleDaily);
                    break;
                case 1:
                    category = context.getResources().getStringArray(R.array.newscatsHKAppleDailyRT);
                    break;
                case 2:
                    category = context.getResources().getStringArray(R.array.newscatsHKOrientalDaily);
                    break;
                case 3:
                    //category = context.getResources().getStringArray(R.array.newscatsHKYahoo);
                    //break;
                //case 4:
                    category = context.getResources().getStringArray(R.array.newscatsHKEJ);
                    break;
                case 4:
                    category = context.getResources().getStringArray(R.array.newscatsHKMetro);
                    break;
                case 5:
                    category = context.getResources().getStringArray(R.array.newscatsHKam730);
                    break;
                case 6:
                    category = context.getResources().getStringArray(R.array.newscatsHKheadline);
                    break;
                case 7:
                    category = context.getResources().getStringArray(R.array.newscatsETNet);
                    break;
                case 8:
                    category = context.getResources().getStringArray(R.array.newscatsTheStandNews);
                    break;
                case 9:
                    category = context.getResources().getStringArray(R.array.newscatsInMediaHK);
                    break;
            }
        } else if (tab.equals("SG")) {
            switch (position) {
                case 0:
                    category = context.getResources().getStringArray(R.array.newscatsZaobao);
                    break;
                case 1:
                    category = context.getResources().getStringArray(R.array.newscatsDaliulian);
                    break;
                case 2:
                    category = context.getResources().getStringArray(R.array.newscatsKwongwah);
                    break;
                case 3:
                    category = context.getResources().getStringArray(R.array.newscatsGuangming);
                    break;
            }
        }

        return category;
    }

    public String[] getFeedURL(String tab, int position) {
        String[] feedURL = null;

        if (tab.equals("TW")) {
            switch (position) {
                case 0:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsCNA);
                    break;
                case 1:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsYahoo);
                    break;
                case 2:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsUDN);
                    break;
                case 3:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsUDNFN);
                    break;
                case 4:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsChinaTimes);
                    break;
                case 5:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsCommercial);
                    break;
                case 6:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsWant);
                    break;
                case 7:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsStorm);
                    break;
                case 8:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsEttoday);
                    break;
                case 9:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsCNYes);
                    break;
                case 10:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsNewsTalk);
                    break;
                case 11:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsLibertyTimes);
                    break;
                case 12:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsAppDaily);
                    break;
                case 13:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsAppDailyRT);
                    break;
                case 14:
                    feedURL = context.getResources().getStringArray(R.array.newsFeedsTheNewsLens);
                    break;
            }
        } else if (tab.equals("HK")) {
            switch (position) {
                case 0:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsHKAppleDaily);
                    break;
                case 1:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsHKAppleDailyRT);
                    break;
                case 2:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsHKOrientalDaily);
                    break;
                case 3:
                    //feedURL = context.getResources().getStringArray(R.array.newsfeedsHKYahoo);
                    //break;
                //case 4:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsHKEJ);
                    break;
                case 4:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsHKMetro);
                    break;
                case 5:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsHKam730);
                    break;
                case 6:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsHKheadline);
                    break;
                case 7:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsETNet);
                    break;
                case 8:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsTheStandNews);
                    break;
                case 9:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsInMediaHK);
                    break;
            }
        } else if (tab.equals("SG")) {
            switch (position) {
                case 0:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsZaobao);
                    break;
                case 1:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsDaliulian);
                    break;
                case 2:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsKwongwah);
                    break;
                case 3:
                    feedURL = context.getResources().getStringArray(R.array.newsfeedsGuangming);
                    break;
            }
        }

        return feedURL;
    }

    public AbstractNews getNewsParser(String tab, int position) {
        AbstractNews parser = null;

        if (tab.equals("TW")) {
            switch (position) {
                case 0:
                    parser = new CNA();
                    break;
                case 1:
                    parser = new Yahoo();
                    break;
                case 2:
                case 3:
                    parser = new UDN();
                    break;
                case 4:
                case 5:
                case 6:
                    parser = new ChinaTimes();
                    break;
                case 7:
                    parser = new Storm();
                    break;
                case 8:
                    parser = new ETToday();
                    break;
                case 9:
                    parser = new CNYes();
                    break;
                case 10:
                    parser = new NewTalk();
                    break;
                case 11:
                    parser = new LibertyTimes();
                    break;
                case 12:
                case 13:
                    parser = new AppleDaily();
                    break;
                case 14:
                    parser = new TheNewsLens();
                    break;
            }
        } else if (tab.equals("HK")) {
            switch (position) {
                case 0:
                case 1:
                    parser = new HKAppleDaily();
                    break;
                case 2:
                    parser = new OrientalDaily();
                    break;
                case 3:
                //    parser = new HKYahoo();
                //    break;
                //case 4:
                    parser = new HKEJ();
                    break;
                case 4:
                    parser = new RTHK();
                    break;
                case 5:
                    parser = new AM730();
                    break;
                case 6:
                    parser = new HKHeadline();
                    break;
                case 7:
                    parser = new ETNet();
                    break;
                case 8:
                    parser = new TheStandNews();
                    break;
                case 9:
                    parser = new InMediaHK();
                    break;
            }
        } else if (tab.equals("SG")) {
            switch (position) {
                case 0:
                    parser = new Zaobao();
                    break;
                case 1:
                    parser = new Daliulian();
                    break;
                case 2:
                    parser = new Kwongwah();
                    break;
                case 3:
                    parser = new Guangming();
                    break;
            }
        }

        return parser;
    }

    public static String getEncoding(String tab, int position) {
        String encoding = "utf-8";

        if (tab.equals("HK")) {
            switch (position) {
                case 6: //HKHeadline
                    encoding = "big-5";
                    break;
            }
        }

        return encoding;
    }

    public static boolean customRSSFeed(String url) {

        if (url.contains("appledaily") || // include TW and HK
                url.contains("am730.com.hk") ||
                url.contains("thegreatdaily")) {
            return true;
        } else {
            return false;
        }

    }
}
