package com.ilab.user.e_lora.activities;

import Rest.ApiClient;
import Rest.ApiInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.ilab.user.e_lora.R;
import model.HitsList;
import model.HitsObject;
import model.Payload_elements;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;

public class Data extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = Data.class.getSimpleName();

    //array of items of data present in the payload
    String [] payload_items;

    //views
    ListView listView;
    Spinner node_spinner;
    TextView temp_txtview, humidity_txtview, bmp_txtview, signal_txtview,  battery_txtview, battery_level_label, bmp_label_txtview;


    ApiInterface apiInterface;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        loadSpinner(node_spinner);
        initializeViews();


    }

    //initialize views
    public void initializeViews(){
        temp_txtview = findViewById(R.id.temp_value_txtview);
        humidity_txtview = findViewById(R.id.humidity_value_txtview);
        bmp_txtview = findViewById(R.id.BMP_value_txtview);
        signal_txtview = findViewById(R.id.signal_level_value_txtview);
        battery_txtview = findViewById(R.id.battery_level_value_txtview);
        battery_level_label = findViewById(R.id.battery_level_label);
        bmp_label_txtview = findViewById(R.id.BMP_label);

        battery_level_label.setVisibility(View.GONE);
        battery_txtview.setVisibility(View.GONE);


    }

    private void loadSpinner(Spinner spinner){

        spinner = findViewById(R.id.data_nodes_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.nodes_elements,
                android.R.layout.simple_spinner_item);

        //specify the layout to use when the list of choices appear
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //apply adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String node = parent.getItemAtPosition(position).toString();
        switch (position){
            case 0:
                break;
            case 1:
                getLotecPayload();
                return;
            case 2:
                getTelkomPayload();
                return;
            default:
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    /**
     * this method is used to retrieve data from node indexed 'lotech'
     */

    private void getLotecPayload(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<HitsObject> call = apiInterface.get_last_record_l();
        call.enqueue(new Callback<HitsObject>() {
            @Override
            public void onResponse(Call<HitsObject> call, Response<HitsObject> response) {
                HitsList hitsList = new HitsList();
                String jsonResponse = "";
                try {
                    if (response.isSuccessful()){
                        hitsList = response.body().getHits();
                    }else {
                        jsonResponse = response.errorBody().string();
                    }
                    Log.d(TAG, "onResponse: hits: " + hitsList);
                    /**
                     * update the UI
                     * with the values returned by the API
                     * TODO Data binding
                     */
                    initializeViews();
                    temp_txtview.setText(new Long(hitsList.getData().get(0).getData_model().getPayload().getTemperature()).toString());
                    humidity_txtview.setText(new Long(hitsList.getData().get(0).getData_model().getPayload().getHumidityy()).toString());
                    bmp_txtview.setText(new Long(hitsList.getData().get(0).getData_model().getPayload().getPressure()).toString());
                    signal_txtview.setText(new Float(hitsList.getData().get(0).getData_model().getMetadata().getFrequency()).toString());

                    battery_txtview.setVisibility(View.GONE);
                    battery_level_label.setVisibility(View.GONE);
                    bmp_txtview.setVisibility(View.VISIBLE);
                    bmp_label_txtview.setVisibility(View.VISIBLE);




                }catch (NullPointerException e){
                    Log.e(TAG, "NULLPOINTER" + e.getMessage());
                }catch (IndexOutOfBoundsException e){
                    Log.e(TAG, "INDEXOUTOFBOUND"+e.getMessage());
                }catch (IOException e){
                    Log.e(TAG, "IOEXCEPTION"+e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<HitsObject> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });

    }
    /**
     * get payload from node indexed 'telkom'
     *
     */

    private void getTelkomPayload(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<HitsObject> call = apiInterface.get_last_record_t();
        call.enqueue(new Callback<HitsObject>() {
            @Override
            public void onResponse(Call<HitsObject> call, Response<HitsObject> response) {
                HitsList hitsList = new HitsList();
                String jsonResponse = "";
                try{
                    if (response.isSuccessful()){
                        hitsList = response.body().getHits();
                    }else {
                        jsonResponse = response.errorBody().string();
                    }
                    /**
                     * update UI
                     * TODO databinding
                     */
                    initializeViews();
                    temp_txtview.setText(new Long(hitsList.getData().get(0).getData_model().getPayload().getTemperature()).toString());
                    humidity_txtview.setText(new Long(hitsList.getData().get(0).getData_model().getPayload().getHumidityy()).toString());

                    //hide unnecessary textviews
                    bmp_label_txtview.setVisibility(View.GONE);
                    bmp_txtview.setVisibility(View.GONE);
                    battery_txtview.setVisibility(View.GONE);
                    battery_level_label.setVisibility(View.GONE);
                    signal_txtview.setText(new Float(hitsList.getData().get(0).getData_model().getMetadata().getFrequency()).toString());


                }catch (NullPointerException e){
                    Log.e(TAG, "NULLPOINTER" +e.getMessage());

                }catch (IndexOutOfBoundsException e){
                    Log.e(TAG, "INDEXOUTOFBOUND" +e.getMessage());

                }catch (IOException e){
                    Log.e(TAG, "IOEXCEPTION" +e.getMessage());

                }
            }

            @Override
            public void onFailure(Call<HitsObject> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });

    }
}
