
package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
        pressure = bundle.getBoolean(PRESSURE);
        wind = bundle.getBoolean(WIND);
        title = findViewById(R.id.text_title);
        Weather weather = Weather.weathers[(int) weatherId];
        title.setText(weather.getName());
        resultTemperature = findViewById(R.id.result_temperature);
        resultTemperature.setText(resultTemperature.getText() + weather.getTemperature());
        imageWeather = findViewById(R.id.image_weather);
        imageWeather.setImageResource(weather.getImageResourceId());
        resultPressure = findViewById(R.id.result_pressure);
        if (pressure) {
            resultPressure.setText(resultPressure.getText() + weather.getPressure());
        } else {
            resultPressure.setVisibility(View.GONE);
        }

        resultWind = findViewById(R.id.result_wind);
        if (wind) {
            resultWind.setText(resultWind.getText() + weather.getWind());
        } else {
            resultWind.setVisibility(View.GONE);
        }

        // weatherResultFragment.setWeather(weatherId);    //не работает!!!
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
}