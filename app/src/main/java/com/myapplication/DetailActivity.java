
package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.myapplication.MainActivity.CheckPressure;
import static com.myapplication.MainActivity.CheckWind;
import static com.myapplication.WeatherResultFragment.PRESSURE;
import static com.myapplication.WeatherResultFragment.WEATHER_ID;
import static com.myapplication.WeatherResultFragment.WIND;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_WEATHER_ID = "id";

    private ImageView imageWeather;
    private TextView title;
    private TextView resultTemperature;
    private TextView resultPressure;
    private TextView resultWind;
    private Button buttonSend;
    private boolean pressure;
    private boolean wind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        WeatherResultFragment weatherResultFragment = (WeatherResultFragment) getSupportFragmentManager().findFragmentById(R.id.detail_frag);
        int weatherId = getIntent().getIntExtra(EXTRA_WEATHER_ID, 0);
        newInstance(CheckPressure, CheckWind, weatherId);

        Bundle bundle = getIntent().getExtras();
        Weather weather = Weather.weathers[weatherId];
        title = findViewById(R.id.text_title);
        title.setText(weather.getName());
        resultTemperature = findViewById(R.id.result_temperature);
        resultTemperature.setText(resultTemperature.getText() + weather.getTemperature());
        imageWeather = findViewById(R.id.image_weather);
        imageWeather.setImageResource(weather.getImageResourceId());
        pressure = bundle.getBoolean(PRESSURE);
        resultPressure = findViewById(R.id.result_pressure);
        if (pressure) {
            resultPressure.setText(resultPressure.getText() + weather.getPressure());
        } else {
            resultPressure.setVisibility(View.GONE);
        }
        wind = bundle.getBoolean(WIND);
        resultWind = findViewById(R.id.result_wind);
        if (wind) {
            resultWind.setText(resultWind.getText() + weather.getWind());
        } else {
            resultWind.setVisibility(View.GONE);
        }
//        buttonSend = findViewById(R.id.send);
//        buttonSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendWeather();
//            }
//        });
    }


    public static Fragment newInstance(boolean pressure, boolean wind, long id) {
        Fragment fragment = new WeatherResultFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(PRESSURE, pressure);
        bundle.putBoolean(WIND, wind);
        bundle.putLong(WEATHER_ID, id);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void sendWeather() {
        String message = (String) resultTemperature.getText();
        // String message = getArguments().getString(RESULT_ACTIVITY_STRING);
        Intent intent = new Intent(Intent.ACTION_SEND);
        // intent.addCategory(Intent.CATEGORY_HOME);     //для проверки защиты от скрашивания
        intent.setType("text/plane");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        // if (intent.resolveActivity(getPackageManager()) != null) {      //защита от скрашивания
        startActivity(intent);
    }

}