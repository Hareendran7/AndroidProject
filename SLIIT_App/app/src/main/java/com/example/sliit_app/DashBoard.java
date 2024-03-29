//Dashboard - Home Screen of the app. Shows automatic slide show of images
package com.example.sliit_app;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;

public class DashBoard extends Fragment {

    //Creates the Slide show using view pager and places it inside the fragment_dashboard
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.fragment_dashboard,container,false);
        final ViewPager viewPager = view.findViewById(R.id.view_pager);
        final ImageAdapter imageAdapter = new ImageAdapter(this.getContext());
        viewPager.setAdapter(imageAdapter);

        //Handles the image transitions sequentially
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            private int currentPage = 0;
            public void run() {
                if (currentPage == 4) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, false);
            }
        };

        //Setting up a timer to automatically do the image transitions
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        return view;

    }


}
