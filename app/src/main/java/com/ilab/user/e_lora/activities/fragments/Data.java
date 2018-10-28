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
import model.HitsList;
import retrofit2.Call;
import utils.PayLoadResponses;
import utils.PayLoadResponsesCallbacks;

/**
 * A simple {@link Fragment} subclass.
 */
public class Data extends Fragment {

    private static final  String TAG = Data.class.getSimpleName();

    ApiInterface apiInterface;
    PayLoadResponses payLoadResponses;

    private TextView temp_value_txtview, humidity_value_txtview, bmp_value_txtview, soilMoisture_value, lux_value;

    public Data() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_data, container, false);
        // Inflate the layout for this fragment
        getData("lotech", rootview);
        return rootview;
    }


    public void getData(String index, final View view){
        payLoadResponses = new PayLoadResponses();
        payLoadResponses.get_most_recent_documents(index, new PayLoadResponsesCallbacks() {
            @Override
            public void onSuccess(HitsList hitsList) {
                getViews(view);
                temp_value_txtview.setText(new Long(hitsList.getData().get(0).getData_model().getPayload().getTemperature()).toString());
                humidity_value_txtview.setText(new Long(hitsList.getData().get(0).getData_model().getPayload().getHumidityy()).toString());
                bmp_value_txtview.setText(new Long(hitsList.getData().get(0).getData_model().getPayload().getPressure()).toString());
                soilMoisture_value.setText(new Long(hitsList.getData().get(0).getData_model().getPayload().getSoil_moisture()).toString());
                lux_value.setText(new Long(hitsList.getData().get(0).getData_model().getPayload().getLux()).toString());
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.e(TAG, "ERROR "+throwable.getMessage());
            }
        });
    }

    public  void getViews(View view){
        temp_value_txtview = view.findViewById(R.id.temp_value_txtview);
        humidity_value_txtview = view.findViewById(R.id.humidity_value_txtview);
        bmp_value_txtview = view.findViewById(R.id.BMP_value_txtview);
        soilMoisture_value = view.findViewById(R.id.soil_moisture_value_txtview);
        lux_value = view.findViewById(R.id.lux_value_txtview);

    }
}
