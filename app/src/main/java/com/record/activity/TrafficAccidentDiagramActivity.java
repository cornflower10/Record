package com.record.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.record.R;
import com.record.adapter.LoadGifAdapter;
import com.record.moudle.entity.ImageLoad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TrafficAccidentDiagramActivity extends BaseActivity {

    @BindView(R.id.rv)
    RecyclerView rv;
    private List<ImageLoad> list = new ArrayList<>();

    @Override
    public int setContentView() {
        return R.layout.activity_traffic_accident_diagram;
    }

    @Override
    public String setTitleName() {
        return "交通事故图解";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String [] urls = null;
        try {
            urls = getAssets().list("gif");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(null!=urls){
            for (int i = 0; i <urls.length ; i++) {
                ImageLoad image = new ImageLoad();
                image.setUrl("file:///android_asset/gif/"+urls[i]);
                list.add(image);
            }
        }
        LoadGifAdapter docTypeAdapter = new LoadGifAdapter(R.layout.gif_item,list);
        rv.setAdapter(docTypeAdapter);
        rv.setLayoutManager(new LinearLayoutManager(mContext));
    }
}
