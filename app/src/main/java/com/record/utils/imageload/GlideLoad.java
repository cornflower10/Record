package com.record.utils.imageload;

import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

import static com.bumptech.glide.Glide.with;


public class GlideLoad implements ImageLoader {
    @Override
    public void showImage(String url, ImageView imageView, int defaultImage) {
        with(imageView.getContext()).load(url).into(imageView);
    }

    @Override
    public void loadLocalImage(String url, ImageView imageView, int defaultImage) {

    }

    @Override
    public void loadCircleImage(String url, ImageView imageView, int defaultImage) {

    }

    @Override
    public void loadVideo(String url, ImageView imageView, int defaultImage) {
            Glide.with( imageView.getContext() )
                .load( Uri.fromFile( new File( url ) ) )
                .into( imageView );
    }

    @Override
    public void loadGif(String url, ImageView imageView, int defaultImage) {
            Glide.with(imageView.getContext()).load(url).asGif()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imageView);
    }
}
