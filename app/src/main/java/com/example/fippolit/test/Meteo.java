package com.example.fippolit.test;

/**
 * Created by fippolit on 17/07/2017.
 *
 * This class is our model and contains the data we will save in the database and show in the user interface.
 * Will contains also methods to access data
 */

public class Meteo {
    private long id;
    private String data;

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

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return data;
    }
}
