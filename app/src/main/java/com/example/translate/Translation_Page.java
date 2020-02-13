package com.example.translate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class Translation_Page extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{
private DrawerLayout mDrawerLayout;
private ActionBarDrawerToggle mToggle;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation__page);
        // Button navigation view
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        mDrawerLayout=findViewById(R.id.drawer);
        mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.Open,R.string.Close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new FragmentHome()).commit();
            navigationView.setCheckedItem(R.id.homeItem);
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
                    Fragment selectedFragment=null;
                    switch (Item.getItemId()){
                        case R.id.home_nav:
                            selectedFragment=new FragmentHome();
                            break;
                        case R.id.favourite_nav:
                            selectedFragment=new FragmentFavourites();
                            break;
                        case R.id.search_nav:
                            selectedFragment=new FragmentHistory();
                            break;
                    }
//
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,selectedFragment).commit();
                    return true;

                }
            };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.homeItem:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new FragmentHome()).commit();
                break;
            case R.id.historyItem:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new FragmentHistory()).commit();
                break;
            case R.id.favouriteItem:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new FragmentFavourites()).commit();
                break;
            case R.id.rateItem:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new FragmentRate()).commit();
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onStart(){
        super.onStart();
        progressDialog=progressDialog.show(this,"Language Translator opening","please wait...",true);
        CountDownTimer timer= new CountDownTimer(2000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                progressDialog.dismiss();
            }
        }.start();
    }
}
