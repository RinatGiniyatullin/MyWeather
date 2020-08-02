package com.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static android.os.Build.ID;

public class WeatherResultFragment extends Fragment {
    final static String WEATHER_ID = "id";
    private long weatherId;

    protected final static String RESULT_ACTIVITY_STRING = "intent";
    public static final String INTENT = "in";
    final static String PRESSURE = "pressure";
    final static String WIND = "wind";
    private ImageView imageWeather;
    private TextView title;
    private TextView resultTemperature;
    private TextView resultPressure;
    private TextView resultWind;
    private Button buttonSend;
    private boolean pressure;
    private boolean wind;
//private final static String DETAIL_FRAGMENT_TAG="2da29b6a-b234-4983-9636-a55a14837bdd";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        pressure = getArguments().getBoolean(PRESSURE);
        wind = getArguments().getBoolean(WIND);
        if (savedInstanceState != null) {
            weatherId = savedInstanceState.getLong(WEATHER_ID);
        }
        return inflater.inflate(R.layout.fragment_weather_result, container, false);
    }

    @Override
    public void onStart() {
        View view = getView();
        if (view != null) {
            title = view.findViewById(R.id.text_title);
            Weather weather = Weather.weathers[(int) weatherId];
            title.setText(weather.getName());
            resultTemperature = view.findViewById(R.id.result_temperature);
            resultTemperature.setText(resultTemperature.getText() + weather.getTemperature());
            imageWeather = view.findViewById(R.id.image_weather);
            imageWeather.setImageResource(weather.getImageResourceId());
            resultPressure = view.findViewById(R.id.result_pressure);
            if (pressure) {
                resultPressure.setText(resultPressure.getText() + weather.getPressure());
            } else {
                resultPressure.setVisibility(View.GONE);
            }

            resultWind = view.findViewById(R.id.result_wind);
            if (wind) {
                resultWind.setText(resultWind.getText() + weather.getWind());
            } else {
                resultWind.setVisibility(View.GONE);
            }
        }

        buttonSend = view.findViewById(R.id.send);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendWeather();
            }
        });

        super.onStart();
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

    public void setWeather(long id) {
        this.weatherId = id;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(ID, weatherId);
    }
}
