package com.example.codek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.tomer.fadingtextview.FadingTextView;

import java.util.concurrent.TimeUnit;

public class SplashScreen extends AppCompatActivity {
private FadingTextView fadingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
fadingTextView =findViewById(R.id.fadingt);
String [] ex={"Code Your Logic EAsily","Code Your Logic EAsily"};
fadingTextView.setTexts(ex);
fadingTextView.setTimeout(800, TimeUnit.MILLISECONDS);
fadingTextView.forceRefresh();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(SplashScreen.this,
                        MainActivity.class);
                //Intent is used to switch from one activity to another.

                startActivity(i);
                //invoke the SecondActivity.

                finish();
                //the current activity will get finished.
            }
        }, 3000);

    }
}