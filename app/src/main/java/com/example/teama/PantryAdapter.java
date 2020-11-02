package com.example.teama;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PantryAdapter extends ArrayAdapter<Pantry_List> {
    private static final String TAG = "PantryAdapter";
    private Context mContext;
    int mResource;

    public PantryAdapter(Context context, int resource, ArrayList<Pantry_List> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        String name = getItem(position).getItem();
        int quantity = getItem(position).getQuantity();
        Pantry_List pantry_item = new Pantry_List(name, quantity);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        TextView tvItem = (TextView) convertView.findViewById(R.id.itemInPantry);
        tvItem.setText(pantry_item.toString());

        return convertView;
    }
}
