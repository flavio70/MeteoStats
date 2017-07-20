package com.example.fippolit.test;

import java.util.ArrayList;
//import com.publicstaticdroidmain.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


/**
 * Created by fippolit on 19/07/2017.
 *
 * this adapter clasee will turn our collection of meteo into individual ListView
 */
@SuppressWarnings("WeakerAccess")
public class MyCustomBaseAdapter extends BaseAdapter {

    private static ArrayList<Meteo> searchArrayList;

    private LayoutInflater mInflater;

    public MyCustomBaseAdapter(Context context, ArrayList<Meteo> results) {
        searchArrayList = results;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return searchArrayList.size();
    }

    public Object getItem(int position) {
        return searchArrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }


    public void add (Meteo meteo) {
        searchArrayList.add(meteo);
        notifyDataSetChanged();

    }

    public void remove (Meteo meteo) {
        searchArrayList.remove(meteo);
        notifyDataSetChanged();

    }


    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.custom_row_view, null);
            holder = new ViewHolder();
            holder.txtData = (TextView) convertView.findViewById(R.id.data);
            holder.txtForecasts = (TextView) convertView
                    .findViewById(R.id.forecasts);
            holder.txtActuals = (TextView) convertView.findViewById(R.id.actuals);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtData.setText(searchArrayList.get(position).getData());
        holder.txtForecasts.setText(searchArrayList.get(position)
                .getForecasts());
        holder.txtActuals.setText(searchArrayList.get(position).getActuals());

        return convertView;
    }

    static class ViewHolder {
        TextView txtData;
        TextView txtForecasts;
        TextView txtActuals;
    }

}
