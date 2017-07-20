package com.example.fippolit.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayWindActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_wind);

        // Get the Intent that started this activity and extract the strings
        Intent intent = getIntent();

        String txtDatatime = intent.getStringExtra(MainActivity.WIND_DATATIME);
        String txtActuals = intent.getStringExtra(MainActivity.WIND_ACTUALS);
        String txtForecasts = intent.getStringExtra(MainActivity.WIND_FORECASTS);


        // Capture the layout's TextView and set the string as its text
        TextView txtViewDataTime = (TextView) findViewById(R.id.data);
        txtViewDataTime.setText(txtDatatime);

        TextView txtViewForecasts = (TextView) findViewById(R.id.forecasts);
        txtViewForecasts.setText(txtForecasts);

        TextView txtViewActuals = (TextView) findViewById(R.id.actuals);
        txtViewActuals.setText(txtActuals);
    }
}
