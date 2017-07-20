package com.example.fippolit.test;

/**
 * Created by fippolit on 17/07/2017.
 *
 * This class is our model and contains the data we will save in the database and show in the user interface.
 * Will contains also methods to access data
 */
@SuppressWarnings("WeakerAccess")
public class Meteo {
    private long id;
    private String data;
    private String forecasts;
    private String actuals;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public String getForecasts() {
        return forecasts;
    }

    public void setForecasts(String forecasts) {
        this.forecasts = forecasts;
    }

    public String getActuals() {
        return actuals;
    }

    public void setActuals(String actuals) {
        this.actuals = actuals;
    }
    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return data;
    }
}
