package com.ccjeng.news.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.ccjeng.news.News;

/**
 * Created by andycheng on 2015/11/28.
 */
public class PreferenceSetting {

    private static final String TAG = "PreferenceSetting";

    public static void getPreference(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        String fontSize = prefs.getString("font_size", "1");
        String fontColor = prefs.getString("font_color", "#000000");
        String bgColor = prefs.getString("bg_color", "#FFFFFF");
        Boolean smartSave = prefs.getBoolean("smart_save", false);
        Boolean enableSmartSave = false;

        if (smartSave) {
            if (!Network.isWifiAvailable(context)) {
                enableSmartSave = true;
            }
        }
        News.setPrefFontSize(fontSize);
        News.setPrefFontColor(fontColor);
        News.setPretBGColor(bgColor);
        News.setPretSmartSave(enableSmartSave);

    }
}