/*
        Copyright (c) 2016, Diego Bezerra <diego.bezerra@gmail.com>
        Permission to use, copy, modify, and/or distribute this software for any purpose
        with or without fee is hereby granted, provided that the above copyright notice
        and this permission notice appear in all copies.
        THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH
        REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND
        FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT, INDIRECT,
        OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE,
        DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS
        ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
        */

package com.example.sliit_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diegodobelo.expandingview.ExpandingItem;
import com.diegodobelo.expandingview.ExpandingList;

public class FacultyofEngineering extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_faculty_of_engineering, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        ExpandingList expandingList = (ExpandingList)view.findViewById(R.id.expanding_list_main);
        ExpandingItem item1 = expandingList.createNewItem(R.layout.expanding_layout);
        ExpandingItem item2 = expandingList.createNewItem(R.layout.expanding_layout);

        /*ExpandingItem extends from View, so you can call
        findViewById to get any View inside the layout*/
        ((TextView) item1.findViewById(R.id.title)).setText("Degree Programmes");
        ((TextView) item2.findViewById(R.id.title)).setText("Lecturers");

        //This will create 5 items
        item1.createSubItems(5);
        item2.createSubItems(5);

        //get a sub item View
        View subItemZero = item1.getSubItemView(0);
        ((TextView) subItemZero.findViewById(R.id.sub_title)).setText("BSc Engineering (Hons) in Civil Engineering");

        subItemZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProgramEngineering()).commit();
            }
        });

        View subItemOne = item1.getSubItemView(1);
        ((TextView) subItemOne.findViewById(R.id.sub_title)).setText("BSc Engineering (Hons) in Materials Engineering");

        View subItemTwo = item1.getSubItemView(2);
        ((TextView) subItemTwo.findViewById(R.id.sub_title)).setText("BSc Engineering (Hons) in Electrical & Electronic Engineering");

        View subItemThree = item1.getSubItemView(3);
        ((TextView) subItemThree.findViewById(R.id.sub_title)).setText("BSc Engineering (Hons) in Mechanical Engineering");

        View subItemZero0 = item2.getSubItemView(0);
        ((TextView) subItemZero0.findViewById(R.id.sub_title)).setText("Lecturer1");

        View subItemOne1= item2.getSubItemView(1);
        ((TextView) subItemOne1.findViewById(R.id.sub_title)).setText("Lecturer2");

        View subItemOne2 = item2.getSubItemView(2);
        ((TextView) subItemOne2.findViewById(R.id.sub_title)).setText("Lecturer3");

        View subItemOne3 = item2.getSubItemView(3);
        ((TextView) subItemOne3.findViewById(R.id.sub_title)).setText("Lecturer4");

        //Setting expanding view icon and it's color
        item1.setIndicatorColorRes(R.color.white);
        item1.setIndicatorIconRes(R.drawable.ic_about_us);

        item2.setIndicatorColorRes(R.color.white);
        item2.setIndicatorIconRes(R.drawable.ic_about_us);
    }


}
