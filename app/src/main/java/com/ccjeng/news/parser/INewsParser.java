package com.ccjeng.news.parser;

import java.io.IOException;

/**
 * Created by andycheng on 2015/11/15.
 */


public interface INewsParser {
    public String parseHtml(final String link, String content) throws IOException;
    // TODO: 2015/11/26 refactor to abs class. 
}

