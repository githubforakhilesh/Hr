package com.experientialetc.Hr;
//import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import com.airbnb.lottie.L;

public class Splash extends AppCompatActivity {

    ImageView backgroundImage,logogif,lottie;
    SharedPreferences sharedPreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introuctory);


        backgroundImage=findViewById(R.id.backroundimg);
        logogif=findViewById(R.id.logogif);
        lottie=findViewById(R.id.lottie);

        backgroundImage.animate().translationY(-3000).setDuration(1000).setStartDelay(4000);
        logogif.animate().translationY(1900).setDuration(1000).setStartDelay(4000);
        lottie.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        sharedPreferences=getSharedPreferences("login_credential",MODE_PRIVATE);


        // Hide ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (sharedPreferences.getString("user","")!="" && sharedPreferences.getString("pass","")!="")
                {

                    Intent i = new Intent(Splash.this, Home.class);
                    startActivity(i);
                    finish();
                }
                else {

                    Intent i = new Intent(Splash.this,Login.class);
                    startActivity(i);
                    finish();
                }
            }
        },2900);
    }

    }
