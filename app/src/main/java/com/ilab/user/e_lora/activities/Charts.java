package com.ilab.user.e_lora.activities;

import Rest.ApiClient;
import Rest.ApiInterface;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.ilab.user.e_lora.R;
import com.ilab.user.e_lora.activities.fragments.HumidityFragment;
import com.ilab.user.e_lora.activities.fragments.TempGraphFragment;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import model.HitsList;
import model.HitsObject;
import model.Temperature;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Date;

public class Charts extends AppCompatActivity {

    private static final String TAG = Charts.class.getSimpleName();

    ApiInterface apiInterface;

    ArrayList<Long> temp_value;
    ArrayList<Long> humidity_value;
    ArrayList<Date> time_value;
    GraphView graphView_t, graphView_h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);

        get_most_recent_records();




    }


    //add fragment
    private void addfragment(){
        TempGraphFragment tempGraphFragment = new TempGraphFragment();
        HumidityFragment humidityFragment = new HumidityFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.charts_fragment1, tempGraphFragment);
//        fragmentTransaction.add(R.id.charts_fragment2, humidityFragment );
        fragmentTransaction.commit();

    }

    private void get_most_recent_records(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<HitsObject> call = apiInterface.get_most_recent_records_l();
        call.enqueue(new Callback<HitsObject>() {
            @Override
            public void onResponse(Call<HitsObject> call, Response<HitsObject> response) {
                HitsList hitsList = new HitsList();
                temp_value = new ArrayList<Long>();
                time_value = new ArrayList<Date>();
                humidity_value = new ArrayList<Long>();
                //TODO do all the unnecessary work in another method

                String jsonresponse = "";
                if (response.isSuccessful()){
                    hitsList = response.body().getHits();

                }else {
                    jsonresponse = response.errorBody().toString();
                }
                for (int i =0; i<hitsList.getData().size();i++){
                    temp_value.add(hitsList.getData().get(i).getData_model().getPayload().getTemperature());
                    humidity_value.add(hitsList.getData().get(i).getData_model().getPayload().getHumidityy());
                    time_value.add(hitsList.getData().get(i).getData_model().getMetadata().getTime());
                }

                /**
                 * temperature graph
                 */
                graphView_t= findViewById(R.id.temp_graph);

                LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
                for ( int i = temp_value.size()-1; i>=0; i--){
                    Log.d(TAG, "TEMP " +hitsList.getData().get(i).getData_model().getPayload().getTemperature());
                    series.appendData(new DataPoint(time_value.get(i), temp_value.get(i)), true, temp_value.size());
                }
                graphView_t.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getApplicationContext()));
                graphView_t.getGridLabelRenderer().setNumHorizontalLabels(5);
                graphView_t.addSeries(series);

                /**
                 * humidity graph
                 */
                int [] x = {12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
                graphView_h = findViewById(R.id.humidity_graph);
                LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>();
                for (int j = humidity_value.size()-1; j>=0; j--){

                    series1.appendData(new DataPoint(time_value.get(j), humidity_value.get(j)), true, humidity_value.size());

                    Log.d(TAG,"HUMIDITY "+hitsList.getData().get(j).getData_model().getPayload().getHumidityy());
                    Log.d(TAG, "TIME"+hitsList.getData().get(j).getData_model().getMetadata().getTime());
                }
                graphView_h.addSeries(series1);



            }

            @Override
            public void onFailure(Call<HitsObject> call, Throwable t) {
                Log.e(TAG, "FAILED "+t.getMessage());
            }
        });

    }
}
