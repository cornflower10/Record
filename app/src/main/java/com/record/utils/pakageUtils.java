package com.record.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.record.R;

import java.io.File;

/**
 * Created by xiejingbao on 2017/8/29.
 */

public class pakageUtils {


    /**
     *
     * @param context
     */
    public static void launchapp(Context context,String type,String path) {
        String pakgeName = null;
        if(type.equals(context.getString(R.string.canon))){
            pakgeName = "jp.co.canon.bsd.ad.pixmaprint";
        } else if(type.equals(context.getString(R.string.other))){
            pakgeName = "com.dynamixsoftware.printershare";
        }

        // 判断是否安装过App，否则去市场下载
        if (isAppInstalled(context, pakgeName)) {
            try {
                String data_type="application/msword";
                Intent i = new Intent(Intent.ACTION_VIEW);
//                ComponentName comp = new ComponentName("com.dynamixsoftware.printershare","com.dynamixsoftware.printershare.ActivityPrintDocuments");
//             ComponentName comp = new ComponentName("jp.co.canon.bsd.ad.pixmaprint","jp.co.canon.bsd.ad.pixmaprint.EulaActivity");
//
//                i.setComponent(comp);
                i.setPackage(pakgeName);
               File f = new File(path);
                f.exists();
                i.setDataAndType(Uri.fromFile(f), data_type);
                context.startActivity(i);
            } catch (Exception e) {
               e.printStackTrace();
            }
//            context.startActivity(context.getPackageManager().getLaunchIntentForPackage(APP_PACKAGE_NAME));
        } else {
            if(type.equals(context.getString(R.string.canon))){
             Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse("http://zsapp.faduit.com.cn:8070/bat/apk/canon.apk"));
            context.startActivity(intent);
            }else
            goToMarket(context, pakgeName);
        }
    }

    /**
     * 检测某个应用是否安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 去市场下载页面
     */
    public static void goToMarket(Context context, String packageName) {
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
        }
    }
}
