package com.experientialetc.Hr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    ActionBar action;
    AppCompatTextView logout;
LinearLayoutCompat home,me,attendance,team,notification;
FrameLayout frameLayout;
    SharedPreferences.Editor editor;
SharedPreferences preferences;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
      action=getSupportActionBar();
//     action.hide();
      home=findViewById(R.id.home_linr_layout);
        me=findViewById(R.id.me_linr_layout);
        frameLayout=findViewById(R.id.frame_layout);
        attendance=findViewById(R.id.attendance_linr_layout);
        logout=findViewById(R.id.logoutt);
        team=findViewById(R.id.team_linr_layout);
        notification=findViewById(R.id.notification_linr_layout);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        // to make the Navigation drawer icon always appear on the action bar
            action.setDisplayHomeAsUpEnabled(true);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        Fragment fragment = new FirstFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment).commit();
        preferences=getSharedPreferences("login_credential", Context.MODE_PRIVATE);
        editor=preferences.edit();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("user","");
                editor.putString("pass","");
                editor.clear();
                editor.apply();
                editor.commit();
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();

            }
        });
          home.setOnClickListener(new View.OnClickListener() {
              @Override
               public void onClick(View view) {
                  action.show();
                  Fragment fragment = new FirstFragment();
                  FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                  fragmentTransaction.replace(R.id.frame_layout, fragment).commit();
              }
});
          me.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  action.hide();
                  Fragment fragment = new SecondFragment();
                  FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                  fragmentTransaction.replace(R.id.frame_layout, fragment).commit();
              }
          });
             attendance.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     action.hide();
                     Fragment fragment = new attendenceFragment();
                     FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                     fragmentTransaction.replace(R.id.frame_layout, fragment).commit();
                 }
             });
           team.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   action.hide();
                   Fragment fragment = new TeamFragment();
                   FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                   fragmentTransaction.replace(R.id.frame_layout, fragment).commit();
               }
           });
           notification.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   action.hide();
                   Fragment fragment = new Notification_frag();
                   FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                   fragmentTransaction.replace(R.id.frame_layout, fragment).commit();
               }
           });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
