package com.zubisofts.gadsleaderboard.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

import com.zubisofts.gadsleaderboard.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        }.start();

    }
}