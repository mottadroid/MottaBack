package com.ottamotta.mottaback.demo;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import de.greenrobot.event.EventBus;

public class AddressFragment extends Fragment {

    private static final int WAIT_BEFORE_REFRESH_TIMEOUT = 2000;

    private EventBus bus = EventBus.getDefault();

    private EditText searchView;
    private ListView listView;

    private AddressAdapter adapter;

    private Handler handler = new Handler();

    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();
        if (!bus.isRegistered(this)) {
            bus.register(this);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.address_fragment, container, false);
        listView = (ListView) root.findViewById(android.R.id.list);
        searchView = (EditText) root.findViewById(R.id.input);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                refreshLocationList();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        return root;
    }

    /**
     * Here we handle result of GetAddressTask
     * @param event
     */
    public void onEvent(GetAddressTask.GotAddressesEvent event) {
        if (getActivity() != null) {
            adapter = new AddressAdapter(context, event.getAddressList());
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    private Runnable refreshLocationsRunnable = new Runnable() {
        @Override
        public void run() {
            String query = searchView.getText().toString();

            //This task will be executed in BackgroundExecutorService
            GetAddressTask task = new GetAddressTask(context, query);
            bus.postSticky(task);
        }
    };

    private void refreshLocationList() {
        handler.removeCallbacks(refreshLocationsRunnable);
        handler.postDelayed(refreshLocationsRunnable, WAIT_BEFORE_REFRESH_TIMEOUT);
    }
}
