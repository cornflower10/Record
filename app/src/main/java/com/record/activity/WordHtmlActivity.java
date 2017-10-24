package com.record.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.record.R;
import com.record.utils.Constants;
import com.record.utils.WordUtil;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;


public class WordHtmlActivity extends BaseActivity {
    @BindView(R.id.tv_right)
    TextView tvRight;
    //html文件存储位置
    private String savePath = null;
    //文档路径
    private  String path ;
    private boolean isPrintRecordFragment;

    @Override
    public int setContentView() {
        return R.layout.html;
    }

    @Override
    public String setTitleName() {
        return "预览";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(null!= getIntent()){
            isPrintRecordFragment = getIntent().getBooleanExtra("printRecordFragment",false);
        }
        if(isPrintRecordFragment){
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText("导出");
        }

         path = getIntent().getStringExtra("path");
        savePath = Constants.htmlPath;
        String name_all = path.substring(path.lastIndexOf("/") + 1, path.length());
        String name = name_all.substring(0, name_all.indexOf("."));
        try {
            WordUtil.convert2Html(path, savePath + "/" + name + ".html");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //WebView加载显示本地html文件
        WebView webView = (WebView) this.findViewById(R.id.office);
        WebSettings webSettings = webView.getSettings();
//        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);

        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        DecimalFormat format = new DecimalFormat("0.00");
        String formatResult = format.format((float) (outMetrics.widthPixels) / (float) 800); //420为html页面的宽度
        Log.i("xxx", "scale = " + Float.valueOf(formatResult));
        //设置初始缩放大小  100%   屏幕宽度 / 网页设置的宽度
        webView.setInitialScale((int) (Float.valueOf(formatResult) * 100));//39

        webView.loadUrl("file://" + savePath + "/" + name + ".html");
    }

    @OnClick(R.id.tv_right)
    public void onViewClicked() {
        showToast("保存路径："+path);
    }
}
