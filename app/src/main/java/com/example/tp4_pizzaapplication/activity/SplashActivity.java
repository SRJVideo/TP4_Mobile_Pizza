package com.example.tp4_pizzaapplication.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import com.example.tp4_pizzaapplication.R;
import com.example.tp4_pizzaapplication.activity.MainActivity;

import java.util.Timer;
import java.util.TimerTask;


/**
 * se charge du temps de changement des activitées de HomeActivity et LoginActivity
 */
public class SplashActivity extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        setContentView(R.layout.activity_splash);
        // 5 seconds pause on splash page
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isLoggedIn()) {
                    //TODO Redirect to "Une activité pour commander une pizza"     ----- A modifier
                    intent = new Intent(context, null);
                    startActivity(intent);
                    finish();
                } else {
                    //Redirect to Accueil Page
                    intent = new Intent(context, AccueilActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 5000);
    }

    public void init() {
        context = this;
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    }
}