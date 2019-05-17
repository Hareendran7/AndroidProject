package com.example.sliit_app;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import android.content.Intent;




public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Replace the standard action bar with a tool bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        //Set a listener to get the click events in the side navigation menu
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggleDrawer = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_drawer_open,R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggleDrawer);

        toggleDrawer.syncState();

        //Open the dashboard when starting the application and after returning
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashBoard()).commit();
            navigationView.setCheckedItem(R.id.nav_dashboard);
        }

    }

    //When back button is pressed close the drawer if it is open
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    //Implements the methods in NavigationView.OnNavigationItemSelectedListener
    //return true - Selects the item when action is triggered/navigation item is clicked
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_dashboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashBoard()).commit();
                break;
            case R.id.nav_foc:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FacultyofComputing()).commit();
                break;
            case R.id.nav_foe:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FacultyofEngineering()).commit();
                break;
            case R.id.nav_fob:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FacultyofBusiness()).commit();
                break;
            case R.id.nav_about_us:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutUs()).commit();
                break;
            case R.id.nav_contact_us:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContactUs()).commit();
                break;
            case R.id.nav_student_web:

                //Opens a website
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sliit.lk/"));
                startActivity(browserIntent);
                Toast.makeText(this, "Opening Student Web",Toast.LENGTH_SHORT).show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
