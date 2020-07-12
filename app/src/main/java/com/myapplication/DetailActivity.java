
package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_WEATHER_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        WeatherResultFragment weatherResultFragment = (WeatherResultFragment) getSupportFragmentManager().findFragmentById(R.id.detail_frag);
        int weatherId = getIntent().getIntExtra(EXTRA_WEATHER_ID, 0);
        weatherResultFragment.setWeather(weatherId);
    }
}