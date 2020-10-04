/*
package com.example.waterusage.model;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.waterusage.DB.DBHelper;
import com.example.waterusage.R;

import java.util.ArrayList;

public class IntakeLogList extends BaseAdapter {
    private Activity context;
    ArrayList waterLog;
    private PopupWindow pwindo;
    DBHelper db;
    BaseAdapter ba;

    public IntakeLogList(Activity context, ArrayList waterLog,DBHelper db) {
        this.context = context;
        this.waterLog=waterLog;
        this.db=db;
    }

    public static class ViewHolder
    {
        TextView textViewId;
        TextView textViewVolume;
        Button editButton;
        Button deleteButton;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            row = inflater.inflate(R.layout.row_item, null, true);

            vh.textViewId = (TextView) row.findViewById(R.id.textViewId);
            vh.textViewVolume = (TextView) row.findViewById(R.id.textViewCountry);
            vh.editButton = (Button) row.findViewById(R.id.edit);
            vh.deleteButton = (Button) row.findViewById(R.id.delete);

            // store the holder with the view.
            row.setTag(vh);
        } else {

            vh = (ViewHolder) convertView.getTag();

        }

        vh.textViewId.setText("" + waterLog.get(position).getId());
        vh.textViewVolume.setText("" + waterLog.get(position).getWaterVolume());
        final int positionPopup = position;
        vh.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              Log.d("Save: ", "" + positionPopup);
                editPopup(positionPopup);

            }
        });
        vh.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Last Index", "" + positionPopup);
                //     Integer index = (Integer) view.getTag();
                db.deleteCountry(waterLog.get(positionPopup));

                //      countries.remove(index.intValue());
                waterLog = (ArrayList) db.getAllCountries();
                Log.d("Country size", "" + waterLog.size());
                notifyDataSetChanged();
            }
        });
        return  row;
    }

    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {
        return position;
    }

    public int getCount() {
        return waterLog.size();
    }
    public void editPopup(final int positionPopup)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View layout = inflater.inflate(R.layout.edit_popup,
                (ViewGroup) context.findViewById(R.id.popup_element));
        pwindo = new PopupWindow(layout, 600, 670, true);
        pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
        final EditText countryEdit = (EditText) layout.findViewById(R.id.editTextCountry);
        final EditText populationEdit = (EditText) layout.findViewById(R.id.editTextPopulation);
        countryEdit.setText(countries.get(positionPopup).getCountryName());
        populationEdit.setText("" + countries.get(positionPopup).getPopulation());
        Log.d("Name: ", "" + countries.get(positionPopup).getPopulation());
        Button save = (Button) layout.findViewById(R.id.save_popup);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String countryStr = countryEdit.getText().toString();
                String population = populationEdit.getText().toString();
                Country country = countries.get(positionPopup);
                country.setCountryName(countryStr);
                country.setPopulation(Long.parseLong(population));
                db.updateCountry(country);
                countries = (ArrayList) db.getAllCountries();
                notifyDataSetChanged();
                for (Country country1 : countries) {
                    String log = "Id: " + country1.getId() + " ,Name: " + country1.getCountryName() + " ,Population: " + country1.getPopulation();
                    // Writing Countries to log
                    Log.d("Name: ", log);
                }
                pwindo.dismiss();
            }
        });
    }



}
*/
