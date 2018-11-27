package com.ilab.user.e_lora.activities.fragments;


import Rest.ApiClient;
import Rest.ApiInterface;
import adapter.GatewaysAdapater;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.ilab.user.e_lora.R;
import model.DataModel;
import model.Gateway_fields;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeviceInfo extends Fragment {

    private static final String TAG = DeviceInfo.class.getSimpleName();

    //views
    private TextView location_label_txtview, location_value_txtview, last_seen_txtview, gateway_id_txtxview, signal_level_txtview;
    private RecyclerView recyclerView;

    private GatewaysAdapater adapater;

    View rootView;

    ApiInterface apiInterface;

    private ArrayList<Gateway_fields> gateway_fields = new ArrayList<>();
    //Nodes_data_charts nodes_data_charts;
    public DeviceInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_device_info, container, false);
        recyclerView = rootView.findViewById(R.id.gateways_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));




        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        getPayload();


    }

    /**
     * receive payload and display to the user
     *
     */
    public void getPayload() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<DataModel> call = apiInterface.getPayLoadData();
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                gateway_fields = response.body().getPayloads().get(0).getMetadata().getGateway_fields();
                recyclerView.setAdapter(new GatewaysAdapater(gateway_fields, getActivity().getBaseContext(), R.layout.gateways_list_item));


            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Log.e(TAG, "ERROR " +t.getMessage());
            }
        });


    }

    public void getViews(View view){
//        location_label_txtview = view.findViewById(R.id.location_label_txtview);
//        location_value_txtview = view.findViewById(R.id.location_value_txtview);
        last_seen_txtview = view.findViewById(R.id.last_seen_value_txtview);
        gateway_id_txtxview = view.findViewById(R.id.gateway_id_value_txtview);
        signal_level_txtview = view.findViewById(R.id.signal_level_value_txtview);
    }

}
