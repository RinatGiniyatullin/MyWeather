package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import static com.myapplication.WeatherResultFragment.PRESSURE;
import static com.myapplication.WeatherResultFragment.WEATHER_ID;
import static com.myapplication.WeatherResultFragment.WIND;

public class MainActivity extends AppCompatActivity implements WeatherListFragment.WeatherListListener {

    //   public static final String TAG = MainActivity.class.getSimpleName();
    private static final String PRESSURE_POSITION = "p";
    private static final String WIND_POSITION = "w";
    static boolean CheckPressure;
    static boolean CheckWind;
    private CheckBox checkPressure;
    private CheckBox checkWind;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPref = getPreferences(Context.MODE_PRIVATE);
        checkPressure = findViewById(R.id.check_pressure);
        checkWind = findViewById(R.id.check_wind);
        checkPressure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckPressure = isChecked;
            }
        });
        checkWind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckWind = isChecked;
            }
        });
        checkPressure.setChecked(getPreferences(MODE_PRIVATE).getBoolean(WIND_POSITION, false));
        checkWind.setChecked(getPreferences(MODE_PRIVATE).getBoolean(PRESSURE_POSITION, false));
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(PRESSURE_POSITION, checkPressure.isChecked());
        editor.putBoolean(WIND_POSITION, checkWind.isChecked());
        editor.apply();
    }

    @Override
    public void itemClicked(long id) {
        View fragmentContainer = findViewById(R.id.weather_detail);
        if (fragmentContainer != null) {
            WeatherResultFragment resultFragment = new WeatherResultFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            Bundle bundle = new Bundle();
            bundle.putBoolean(PRESSURE, CheckPressure);
            bundle.putBoolean(WIND, CheckWind);
            resultFragment.setArguments(bundle);

            resultFragment.setWeather(id);
            transaction.replace(R.id.weather_detail, resultFragment);
            transaction.commit();
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WEATHER_ID, (int) id);
            intent.putExtra(PRESSURE, CheckPressure);
            intent.putExtra(WIND, CheckWind);
            startActivity(intent);
        }
    }
}