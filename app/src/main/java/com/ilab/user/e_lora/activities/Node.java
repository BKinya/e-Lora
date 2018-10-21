package com.ilab.user.e_lora.activities;

import Rest.ApiClient;
import Rest.ApiInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.ilab.user.e_lora.R;
import model.Humidity;
import model.HumidityAggregation;
import model.TempAggregation;
import model.Temperature;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Node extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private static final String TAG = Node.class.getSimpleName();

    ArrayAdapter<CharSequence> adapter;
    //views
    private Spinner nodes_spinner;
    private Button viewCharts_btn;
    private TextView avgTemp_txtview, avgHumidity_txtview;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node);
        nodes_spinner = findViewById(R.id.node_spinner);
        loadSpinner(nodes_spinner);
        isButtonClicked(viewCharts_btn);



    }

    private void loadSpinner(Spinner spinner) {


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.nodes_elements,
                android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //apply adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
        switch (position){
            case 0:
                break;
            case 1:
                lotecAggr();
                break;
            case 2:
                telKomAggr();
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //handle button click event
    private void isButtonClicked(Button button) {
        button = findViewById(R.id.node_charts_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Node.this, Charts.class));
            }
        });
    }

    private void getTempAvg() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<TempAggregation> call = apiInterface.get_avg_temperature();
        call.enqueue(new Callback<TempAggregation>() {
            @Override
            public void onResponse(Call<TempAggregation> call, Response<TempAggregation> response) {
                Temperature temperature = new Temperature();
                String jsonResponse = "";
                try{
                    if (response.isSuccessful()){
                        temperature = response.body().getTemperature();
                    }else {
                        jsonResponse = response.errorBody().toString();
                    }

                    avgTemp_txtview = findViewById(R.id.avg_temp_value_txtview);
                    avgTemp_txtview.setText(new Double(temperature.getAvg_temp().getValue()).toString());

                }catch (NullPointerException e){
                    Log.e(TAG, "NULLPOINTEREXCEPTION " +e.getMessage());

                }catch (IndexOutOfBoundsException e){
                    Log.e(TAG, "INDEXOUTOFBOUND " +e.getMessage());

                }


            }

            @Override
            public void onFailure(Call<TempAggregation> call, Throwable t) {
                Log.e(TAG, "FAILED " +t.getMessage());
            }
        });
    }

    private void getHumidityAvg() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<HumidityAggregation> call = apiInterface.get_avg_humidity();
        call.enqueue(new Callback<HumidityAggregation>() {
            @Override
            public void onResponse(Call<HumidityAggregation> call, Response<HumidityAggregation> response) {
                Humidity humidity = new Humidity();
                String jsonResponse = "";
                try {
                    if (response.isSuccessful()){
                        humidity = response.body().getHumidity();
                    }else {
                        jsonResponse = response.errorBody().toString();
                    }

                    avgHumidity_txtview = findViewById(R.id.humidity_value_txt_view);
                    avgHumidity_txtview.setText(new Double(humidity.getAvg_humidity().getValue()).toString());
                }catch (NullPointerException e){
                    Log.e(TAG, "NULLPOINTER "+e.getMessage());

                }catch (IndexOutOfBoundsException e){
                    Log.e(TAG, "INDEXOUTOFBOUND "+e.getMessage());

                }

            }

            @Override
            public void onFailure(Call<HumidityAggregation> call, Throwable t) {
                Log.e(TAG, "FAILED " +t.getMessage());
            }
        });

    }

    /**-------------------------------------
     * telkom
     ---------------------------------------*/
    public void getAvgTemp_t(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<TempAggregation> call = apiInterface.get_avg_temperature_t();
        call.enqueue(new Callback<TempAggregation>() {
            @Override
            public void onResponse(Call<TempAggregation> call, Response<TempAggregation> response) {
                Temperature temperature = new Temperature();
                String jsonresponse = "";
                try{
                    if (response.isSuccessful()){
                        temperature = response.body().getTemperature();
                    }else{
                        jsonresponse = response.errorBody().toString();
                    }

                    avgTemp_txtview = findViewById(R.id.avg_temp_value_txtview);
                    avgTemp_txtview.setText(new Double(temperature.getAvg_temp().getValue()).toString());


                }catch (NullPointerException e){
                    Log.e(TAG, "NULLPOINTER" +e.getMessage());
                }catch (IndexOutOfBoundsException e){
                    Log.e(TAG, "INDEXOUTOFBOUND"+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<TempAggregation> call, Throwable t) {
                Log.e(TAG, "FAILED " +t.getMessage());
            }
        });
    }

    public void getAvgHumidity_t(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<HumidityAggregation> call = apiInterface.get_avg_humidity_t();
        call.enqueue(new Callback<HumidityAggregation>() {
            @Override
            public void onResponse(Call<HumidityAggregation> call, Response<HumidityAggregation> response) {
                Humidity humidity = new Humidity();
                String jsonResponse = "";

                try{
                    if (response.isSuccessful()){
                        humidity = response.body().getHumidity();
                    }else {
                        jsonResponse = response.errorBody().toString();
                    }

                    avgHumidity_txtview = findViewById(R.id.humidity_value_txt_view);
                    avgHumidity_txtview.setText(new Double(humidity.getAvg_humidity().getValue()).toString());
                }catch (NullPointerException e){
                    Log.e(TAG, "NULLPOINTER" +e.getMessage());
                }catch (IndexOutOfBoundsException e){
                    Log.e(TAG, "INDEXOUTOFBOUND"+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<HumidityAggregation> call, Throwable t) {
                Log.e(TAG, "FAILED " +t.getMessage());
            }
        });
    }
    /**
     * return average humidity and temperature of node indexed lotech
     */
    private void lotecAggr(){
        getTempAvg();
        getHumidityAvg();
    }

    /**
     * return average humidity and tempeerature of node indexed telkom
     */

    private void telKomAggr(){
        getAvgTemp_t();
        getAvgHumidity_t();
    }
}


