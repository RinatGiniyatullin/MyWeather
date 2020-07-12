package com.myapplication;

import androidx.annotation.NonNull;

class Weather {
    private String name;
    private String temperature;
    private String pressure;
    private String wind;
    private int imageResourceId;

    private Weather(String name, String temperature, String pressure, String wind, int imageResourceId) {
        this.name = name;
        this.temperature = temperature;
        this.pressure = pressure;
        this.wind = wind;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public String getWind() {
        return wind;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }

    static final Weather[] weathers = {
            new Weather("Москва",
                    "+21 град. С",
                    "765 мм.рт.ст",
                    "5 м/c",
                    R.drawable.moscow),
            new Weather("Казань",
                    "+23 град. С",
                    "768 мм.рт.ст",
                    "7 м/c",
                    R.drawable.kazan),
            new Weather("Самара",
                    "+29 град. С",
                    "770 мм.рт.ст",
                    "4 м/c",
                    R.drawable.samara)
    };
}
