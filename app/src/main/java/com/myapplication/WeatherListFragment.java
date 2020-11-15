package com.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

public class WeatherListFragment extends ListFragment {
    private WeatherListListener mainActivity;

    public interface WeatherListListener {
        void itemClicked(long id);
    }

    @Override
    public void onAttach(Context context) {
        mainActivity = (WeatherListListener) context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String[] cities = new String[Weather.weathers.length];
        for (int i = 0; i < cities.length; i++) {
            cities[i] = Weather.weathers[i].getName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_list_item_1, cities);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mainActivity.itemClicked(id);
    }
}
