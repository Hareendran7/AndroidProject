package com.example.sliit_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageAdapter extends PagerAdapter {

    private Context context;
    private int[] imageList = new int[]{R.raw.sliit_image_1, R.raw.sliit_image_2, R.raw.sliit_image_3, R.raw.sliit_image_4};

    ImageAdapter(Context pContext) {
        context = pContext;
    }

    //Return the number of images
    @Override
    public int getCount() {
        return imageList.length;
    }

    //Checks if the image view equals to the object identifier
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == o);
    }

    //Setting up the image inside the image view
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(imageList[position]);
        container.addView(imageView, 0);

        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }


}
