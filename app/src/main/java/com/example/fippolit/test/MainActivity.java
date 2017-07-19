package com.example.fippolit.test;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


//MainActivity is the application entry point
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
        List<Meteo> values = datasource.getAllMeteos();

        // print some log stuff
        for (Meteo meteo : values) {
            String log = "Id: " + meteo.getId() + " ,value: " + meteo.getData();
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

        //listView item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item
                Meteo  itemValue    = (Meteo) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+position+"  ListItem : " +itemValue.getData() , Toast.LENGTH_LONG)
                        .show();

            }
        });
    }

    /** Called when the user taps the addNew button */
    public void clickButton(View view) {
        // Do something in response to button
        ArrayAdapter<Meteo> adapter = (ArrayAdapter<Meteo>) listView.getAdapter();
        Meteo meteo = null;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMMM/d - HH:mm");
        String strCurrDate = sdf.format(cal.getTime());

        switch (view.getId()) {
            case R.id.add:
                Log.d("clickButton","adding item to db...");
                if (listView.getAdapter().getCount() > 0) {
                    meteo = (Meteo) listView.getAdapter().getItem(listView.getAdapter().getCount()-1);
                    try {
                        Date dbDate = sdf.parse(meteo.getData());
                        Date currDate = sdf.parse(strCurrDate);
                        if(currDate.after(dbDate)){
                            Log.d("clickButton","added item "+ strCurrDate +" to db");
                            meteo = datasource.createMeteo(strCurrDate);
                            adapter.add(meteo);
                        } else {
                            Log.d("clickButton","Item "+strCurrDate+" NOT added to db. Already present");
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                } else {
                    Log.d("clickButton","added item to db, first item");
                    meteo = datasource.createMeteo(strCurrDate);
                    adapter.add(meteo);
                }
                break;
            case R.id.delete:
                Log.d("clickButton","removing item from db...");
                if (listView.getAdapter().getCount() > 0) {
                    meteo = (Meteo) listView.getAdapter().getItem(listView.getAdapter().getCount()-1);
                    datasource.deleteMeteo(meteo);
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
