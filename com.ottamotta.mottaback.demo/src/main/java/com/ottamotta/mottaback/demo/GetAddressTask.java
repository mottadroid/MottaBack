package com.ottamotta.mottaback.demo;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.ottamotta.mottaback.MottaTask;

import java.util.List;
import java.util.Locale;

public class GetAddressTask extends MottaTask<GetAddressTask.GotAddressesEvent>{

    private Context context;
    private String query;

    public GetAddressTask(Context context, String query) {
        this.context = context;
        this.query = query;
    }

    @Override
    public GotAddressesEvent getTaskResult() {
        try {
            Geocoder geocoder;
            geocoder = new Geocoder(context, new Locale("en"));
            List<Address> addresses = geocoder.getFromLocationName(query, 10);
            return new GotAddressesEvent(addresses);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class GotAddressesEvent {

        private List<Address> data;

        public GotAddressesEvent(List<Address> data) {
            this.data = data;
        }

        public List<Address> getAddressList() {
            return data;
        }
    }

}
