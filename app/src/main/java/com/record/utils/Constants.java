package com.record.utils;

import android.os.Environment;
import android.util.SparseArray;

/**
 * Created by 灌云县公安局 李秉键 on 2017/9/3.
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
    public static final String DOC_NULL = "always_doc_null";

    public static final int TYPE_ACCI = 1;//交通事故
    public static final int TYPE_CRIMINAL = 2;//刑事案件
    public static final int TYPE_ADMINISTRATIVE_CASE = 3;//行政案件
    public static final int TYPE_ADMINISTRATIVE_DOC = 4;//会议公文

    public static final SparseArray<String> SPARSEARRAY_NAME = new SparseArray<>();
    static {
        SPARSEARRAY_NAME.append(TYPE_ACCI,"交通事故");
        SPARSEARRAY_NAME.append(TYPE_CRIMINAL,"刑事案件");
        SPARSEARRAY_NAME.append(TYPE_ADMINISTRATIVE_CASE,"行政案件");
        SPARSEARRAY_NAME.append(TYPE_ADMINISTRATIVE_DOC,"会议公文");


    }

    //录入人员，车辆，案情，执法
    public static final int AUTHOR = 1;
    public static final int CAR = 2;
    public static final int LAWCASE = 3;
    public static final int ENFORCE_LAW = 4;


    public static final SparseArray<String> TEMP_TYPE = new SparseArray<>();
    static {
        TEMP_TYPE.append(AUTHOR,"录入人员");
        TEMP_TYPE.append(CAR,"车辆");
        TEMP_TYPE.append(LAWCASE,"案情");
        TEMP_TYPE.append(ENFORCE_LAW,"执法");


    }

    public static final String INVOLVED_ ="involved_";
    public static final String THING_ ="thing_";
    public static final String CAR_ ="car_";

}
