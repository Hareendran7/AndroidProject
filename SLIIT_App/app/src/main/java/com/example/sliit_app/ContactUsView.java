package com.example.sliit_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class ContactUsView extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus_view);

        mRecyclerView = (RecyclerView) findViewById(R.id.details);
        back = (Button)findViewById(R.id.btn_back1);


        //Button click to go back to Contact Us page
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContactUs()).commit();

            }
        });


        //Loading data into RecyclerView from database
        new FirebaseDatabaseHelper().readDetails(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<ContactUsClass> contacts, List<String> keys) {
                findViewById(R.id.loading_contact_list).setVisibility(View.GONE);
                new RecyclerView_Config().setConfig(mRecyclerView, ContactUsView.this, contacts, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }

}