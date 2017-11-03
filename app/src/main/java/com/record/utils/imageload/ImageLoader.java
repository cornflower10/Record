package com.record.utils.imageload;

import android.widget.ImageView;


public interface ImageLoader {

    void showImage(String url, ImageView imageView, int defaultImage);

    void loadLocalImage(String url, ImageView imageView, int defaultImage);

    void loadCircleImage(String url, ImageView imageView, int defaultImage);

    void loadVideo(String url, ImageView imageView, int defaultImage);

    void loadGif(String url, ImageView imageView, int defaultImage);

}
