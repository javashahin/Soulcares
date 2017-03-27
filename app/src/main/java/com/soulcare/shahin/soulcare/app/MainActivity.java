package com.soulcare.shahin.soulcare.app;

import android.content.Intent;
import android.graphics.Color;

import android.os.Build;
import android.os.Bundle;


import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth auth;

    FloatingActionMenu _materialDesignFAM;
    FloatingActionButton _floatingActionButtonLogout, _floatingActionButtonKnow, _floatingActionButtonMsg, _floatingActionButtonAlarm;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Get


        if (toolbar != null) {
            setSupportActionBar(toolbar);

            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("Menu");
            }
        }
        _materialDesignFAM = (FloatingActionMenu) findViewById(R.id.social_floating_menu);
        _floatingActionButtonLogout = (FloatingActionButton) findViewById(R.id.floating_lock);
        _floatingActionButtonKnow = (FloatingActionButton) findViewById(R.id.floating_know);
        _floatingActionButtonMsg = (FloatingActionButton) findViewById(R.id.floating_msg);
        _floatingActionButtonAlarm = (FloatingActionButton) findViewById(R.id.floating_alarm);






        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        List<Data> data = fill_with_data();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        Recycler_View_Adapter adapter = new Recycler_View_Adapter(data, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);

        auth = FirebaseAuth.getInstance();


        _floatingActionButtonLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // User is logged in
                   if (auth.getCurrentUser() != null) {

                        auth.signOut();
                       startActivity(new Intent(MainActivity.this, LoginActivityUser.class));
                       finish();

                       Toast.makeText(MainActivity.this, "Logout Successfull", Toast.LENGTH_SHORT).show();
                       overridePendingTransition(R.anim.fab_scale_down,R.anim.fab_scale_down);

                   }


           }
       });


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // perform query here

                // workaround to avoid issues with some emulators and keyboard devices firing twice if a keyboard enter is used
                // see https://code.google.com/p/android/issues/detail?id=24599
                searchView.clearFocus();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        int searchEditId = android.support.v7.appcompat.R.id.search_src_text;
        EditText et = (EditText) searchView.findViewById(searchEditId);
        et.setTextColor(Color.WHITE);
        et.setHintTextColor(Color.WHITE);
        return super.onCreateOptionsMenu(menu);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        int id = item.getItemId();

        if (id == R.id.nav_Profile) {





        } else if (id == R.id.nav_Food) {

        } else if (id == R.id.nav_Routine) {

        }  else if (id == R.id.nav_Madicine) {

        }
        else if (id == R.id.Nav_deangour) {

        }
        else if (id == R.id.Nav_near_Hospital) {

        }

        else if (id == R.id.Nav_care){

        } else if (id == R.id.Nav_Extra_Care) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);

    }
    public List<Data> fill_with_data() {

        List<Data> data = new ArrayList<>();

        data.add(new Data("Possible Pregnancy ", "Following the destruction of Metropolis,", R.drawable.setting));
        data.add(new Data("View Next Period ", "X-Men: Apocalypse is an upcoming American superhero film  ", R.drawable.medicine));
        data.add(new Data("Flerity Date", "A feud between Captain America and Iron Man leaves the Avengers in turmoil.  ", R.drawable.alert));
        data.add(new Data("Next Check Up Date", "After reuniting with his long-lost father, Po  must train a village of pandas", R.drawable.food));
        data.add(new Data("Ovalution ", "Fleeing their dying home to colonize another, ", R.drawable.alarm));
        data.add(new Data("Calendar", "Alice in Wonderland: Through the Looking Glass ", R.drawable.hospital));

        return data;
    }

    public void showDatePickerDialogperiod(View v) {
        DialogFragment newFragment = new PeriodDatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");




    }
    public void showDatePregnant(View v) {
        DialogFragment newFragment = new PregDatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");




    }

}


