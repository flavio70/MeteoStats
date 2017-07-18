package com.example.fippolit.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private MeteosDataSource datasource;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);


        Log.d("Init","Initializing DB resources...");
        datasource = new MeteosDataSource(this);
        datasource.open();
        Log.d("Init","Getting DB resources...");

        // get db record to display in ListView
        List<Meteo> values = datasource.getAllComments();

        // print some log stuff
        for (Meteo meteo : values) {
            String log = "Id: " + meteo.getId() + " ,value: " + meteo.getComment();
            // Writing DB entries to log
            Log.d("Meteo:: ", log);
        }
        // Define a new Adapter
        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        ArrayAdapter<Meteo> adapter = new ArrayAdapter<Meteo>(this,
                android.R.layout.simple_list_item_1, values);

        // Assign adapter to ListView
        listView.setAdapter(adapter);
    }

    /** Called when the user taps the addNew button */
    public void clickButton(View view) {
        // Do something in response to button
        ArrayAdapter<Meteo> adapter = (ArrayAdapter<Meteo>) listView.getAdapter();
        Meteo meteo = null;
        switch (view.getId()) {
            case R.id.add:
                Log.d("clickButton","adding item to db...");
                String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
                int nextInt = new Random().nextInt(3);
                // save the new meteo to the database
                meteo = datasource.createComment(comments[nextInt]);
                adapter.add(meteo);
                break;
            case R.id.delete:
                Log.d("clickButton","removing item to db...");
                if (listView.getAdapter().getCount() > 0) {
                    meteo = (Meteo) listView.getAdapter().getItem(0);
                    datasource.deleteComment(meteo);
                    adapter.remove(meteo);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

}
