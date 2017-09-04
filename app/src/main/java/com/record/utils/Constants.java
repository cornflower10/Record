package com.record.utils;

import android.os.Environment;

/**
 * Created by xiejingbao on 2017/9/3.
 */

public class Constants {
    public static final String doc ="/doc";
    public static final String html ="/html";
    public static final String dir = "/com.record";
    public static final String pathDir = Environment.getExternalStorageDirectory().getPath();
    public static final String docPath =pathDir+dir+doc;
    public static final String htmlPath =pathDir+dir+html;

    public static final String TYPE = "type";
    public static final String DOC_SETTING = "always_doc_setting";


}
