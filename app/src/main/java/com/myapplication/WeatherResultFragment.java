package com.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
import static com.myapplication.MainActivity.CheckPressure;
import static com.myapplication.MainActivity.CheckWind;

public class WeatherResultFragment extends Fragment {
    final static String WEATHER_ID = "id";
    private long weatherId;

    final static String PRESSURE = "pressure";
    final static String WIND = "wind";
    private final static String DETAIL_FRAGMENT_TAG = "2da29b6a-b234-4983-9636-a55a14837bdd";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    //    setRetainInstance(true);
        FragmentManager fragmentManager = getChildFragmentManager();
        DetailFragment detailFragment = (DetailFragment) fragmentManager.findFragmentByTag(DETAIL_FRAGMENT_TAG);
        if (detailFragment == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            detailFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putBoolean(PRESSURE, CheckPressure);
            bundle.putBoolean(WIND, CheckWind);
            detailFragment.setArguments(bundle);

            fragmentTransaction.replace(R.id.detail_container, detailFragment, DETAIL_FRAGMENT_TAG);
            fragmentTransaction.commit();
        }
        return inflater.inflate(R.layout.fragment_weather_result, container, false);
    }

    @Override
    public void onStart() {
        View view = getView();
        if (view != null) {
            TextView title = view.findViewById(R.id.text_title);
            Weather weather = Weather.weathers[(int) weatherId];
            title.setText(weather.getName());
            ImageView imageWeather = view.findViewById(R.id.image_weather);
            imageWeather.setImageResource(weather.getImageResourceId());
        }
        super.onStart();
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
