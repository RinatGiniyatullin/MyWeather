/*
package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherResultActivity extends AppCompatActivity {
    protected final static String RESULT_ACTIVITY_STRING = "intent";
    public static final String INTENT = "in";
    protected final static String PRESSURE = "pressure";
    protected final static String WIND = "wind";
    private ImageView imageWeather;
    private TextView title;
    private TextView resultTemperature;
    private TextView resultPressure;
    private TextView resultWind;
    private Button buttonSend;
    private Boolean pressure;
    private Boolean wind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_weather);

        resultTemperature = findViewById(R.id.result_temperature);
        resultPressure = findViewById(R.id.result_pressure);
        resultWind = findViewById(R.id.result_wind);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int cityNom = bundle.getInt(INTENT);
            Weather weather = Weather.weathers[cityNom];
            resultTemperature.setText(resultTemperature.getText() + weather.getTemperature());
            boolean pressure = bundle.getBoolean(PRESSURE);
            if (pressure) {
                resultPressure.setText(resultPressure.getText() + weather.getPressure());
            } else {
                resultPressure.setVisibility(View.GONE);
            }
            boolean wind = bundle.getBoolean(WIND);
            if (wind) {
                resultWind.setText(resultWind.getText() + weather.getWind());
            } else {
                resultWind.setVisibility(View.GONE);
            }
        }

        buttonSend = findViewById(R.id.send);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendWeather();
            }
        });
    }

    public void sendWeather() {
        String message = getIntent().getStringExtra(RESULT_ACTIVITY_STRING);
        Intent intent = new Intent(Intent.ACTION_SEND);
        // intent.addCategory(Intent.CATEGORY_HOME);     //для проверки защиты от скрашивания
        intent.setType("text/plane");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {      //защита от скрашивания
            startActivity(intent);
        }
    }
}
*/
