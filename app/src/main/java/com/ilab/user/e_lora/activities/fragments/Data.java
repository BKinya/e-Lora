package com.ilab.user.e_lora.activities.fragments;


import Rest.ApiClient;
import Rest.ApiInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import com.ilab.user.e_lora.R;
import model.DataFields;
import model.DataModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Data extends Fragment {

    private static final  String TAG = Data.class.getSimpleName();

    ApiInterface apiInterface;


    String node_selected;
    private ArrayList<DataFields> dataFields = new ArrayList<>();



    private TextView temp_value_txtview, humidity_value_txtview, soil_moisture_txtview, pressure_txtview, lux_txtview;
    View rootview;

    public Data() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_data, container, false);
        // Inflate the layout for this fragment
        temp_value_txtview = rootview.findViewById(R.id.temp_value_txtview);
        humidity_value_txtview = rootview.findViewById(R.id.humidity_value_txtview);
        soil_moisture_txtview = rootview.findViewById(R.id.soil_moisuture_value_txtview);
        pressure_txtview = rootview.findViewById(R.id.pressure_value);
        lux_txtview = rootview.findViewById(R.id.lux_value);

        return rootview;
    }

    @Override
    public void onResume() {
       super.onResume();
       getData();

//
    }

    private void getData(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<DataModel> call = apiInterface.getPayLoadData();
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                dataFields = response.body().getPayloads();

                //string values from the payload
                String temperature = String.valueOf(dataFields.get(0).getPayload_fields().getTemperature());
                String humidity = String.valueOf(dataFields.get(0).getPayload_fields().getHumidity());
                String soilMoisture = String.valueOf(dataFields.get(0).getPayload_fields().getSoil_moisture());
                String pressure = String.valueOf(dataFields.get(0).getPayload_fields().getPressure());
                String lux = String.valueOf(dataFields.get(0).getPayload_fields().getLux());

                //set values on the respective text views
                temp_value_txtview.setText(temperature);
                humidity_value_txtview.setText(humidity);
                soil_moisture_txtview.setText(soilMoisture);
                pressure_txtview.setText(pressure);
                lux_txtview.setText(lux);

            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Log.e(TAG, "ERROR " + t.getMessage());
            }
        });

    }




}
