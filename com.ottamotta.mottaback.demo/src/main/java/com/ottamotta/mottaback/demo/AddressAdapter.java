package com.ottamotta.mottaback.demo;

import android.content.Context;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddressAdapter extends ArrayAdapter<Address> {

    public AddressAdapter(Context context, List<Address> data) {
        super(context, 0, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.adresses, parent, false);
        Address address = getItem(position);

        TextView location = (TextView) convertView.findViewById(R.id.location);
        TextView subLocation = (TextView) convertView.findViewById(R.id.sub_location);

        location.setText(address.getAddressLine(0));
        if (address.getLocality()!=null) {
            subLocation.setText(address.getLocality() + ", " + address.getCountryName());
        } else {
            subLocation.setText(address.getCountryName());
        }
        return convertView;
    }

}