package com.example.foodtask.utils.image.loader.glide;

import android.widget.ImageView;

import com.example.foodtask.utils.image.loader.IImageLoader;

public final class GlideImageLoader implements IImageLoader {

    @Override
    public void loadImageInTarget(String path, ImageView imageView) {
        GlideApp
                .with(imageView.getContext())
                .load(path)
                .optionalFitCenter()
                .into(imageView);
    }
}
