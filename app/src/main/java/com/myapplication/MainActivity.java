package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import static com.myapplication.WeatherResultFragment.PRESSURE;
import static com.myapplication.WeatherResultFragment.WIND;

public class MainActivity extends AppCompatActivity implements WeatherListFragment.WeatherListListener {

    public static final String TAG = MainActivity.class.getSimpleName();
    private static final String PRESSURE_POSITION = "p";
    private static final String WIND_POSITION = "w";
    private static boolean CHECK_PRESSURE;
    private static boolean CHECK_WIND;
    private CheckBox checkPressure;
    private CheckBox checkWind;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //      Log.d(TAG, "Create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.list_item, getStringArray(Weather.weathers));
//        listView = findViewById(R.id.list_city);
//        listView.setAdapter(listAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                setCitiesWeather(id);
//            }
//        });

        sharedPref = getPreferences(Context.MODE_PRIVATE);
        checkPressure = findViewById(R.id.check_pressure);
        checkWind = findViewById(R.id.check_wind);
        checkPressure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CHECK_PRESSURE = isChecked;
            }
        });
        checkWind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CHECK_WIND = isChecked;
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
            bundle.putBoolean(PRESSURE, CHECK_PRESSURE);
            bundle.putBoolean(WIND, CHECK_WIND);
            resultFragment.setArguments(bundle);

            resultFragment.setWeather(id);
            transaction.replace(R.id.weather_detail, resultFragment);
            transaction.commit();
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WEATHER_ID, (int) id);
            intent.putExtra(PRESSURE, CHECK_PRESSURE);
            intent.putExtra(WIND, CHECK_WIND);
            startActivity(intent);
        }


    }
}