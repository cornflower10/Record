package com.record.utils;

import android.os.Environment;
import android.util.SparseArray;

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

    public static final int TYPE_ACCI = 1;//交通事故
    public static final int TYPE_CRIMINAL = 2;//刑事案件
    public static final int TYPE_ADMINISTRATIVE_CASE = 3;//行政案件
    public static final int TYPE_ADMINISTRATIVE_DOC = 4;//行政公文

    public static final SparseArray<String> SPARSEARRAY_NAME = new SparseArray<>();
    static {
        SPARSEARRAY_NAME.append(TYPE_ACCI,"交通事故");
        SPARSEARRAY_NAME.append(TYPE_CRIMINAL,"刑事案件");
        SPARSEARRAY_NAME.append(TYPE_ADMINISTRATIVE_CASE,"行政案件");
        SPARSEARRAY_NAME.append(TYPE_ADMINISTRATIVE_DOC,"行政公文");


    }
    public static final String NAME = "$NAME$";


}
