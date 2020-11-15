package com.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static android.os.Build.ID;
import static com.myapplication.WeatherResultFragment.PRESSURE;
import static com.myapplication.WeatherResultFragment.WIND;

public class DetailFragment extends Fragment {
    private boolean pressure;
    private boolean wind;
    private TextView resultTemperature;
    private TextView resultPressure;
    private TextView resultWind;
    private Button buttonSend;
    private long weatherId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = getView();
        if (view != null) {
        //if (savedInstanceState != null) {

            Weather weather = Weather.weathers[(int) weatherId];
            resultTemperature = view.findViewById(R.id.result_temperature);
            resultTemperature.setText(resultTemperature.getText() + weather.getTemperature());
            pressure = getArguments().getBoolean(PRESSURE);
            resultPressure = view.findViewById(R.id.result_pressure);
            if (pressure) {
                resultPressure.setText(resultPressure.getText() + weather.getPressure());
            } else {
                resultPressure.setVisibility(View.GONE);
            }
            wind = getArguments().getBoolean(WIND);
            resultWind = view.findViewById(R.id.result_wind);
            if (wind) {
                resultWind.setText(resultWind.getText() + weather.getWind());
            } else {
                resultWind.setVisibility(View.GONE);
            }
            buttonSend = view.findViewById(R.id.send);
            buttonSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendWeather();
                }
            });
        }
        View layout = inflater.inflate(R.layout.activity_detail_fragment, container, false);


        return layout;

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
