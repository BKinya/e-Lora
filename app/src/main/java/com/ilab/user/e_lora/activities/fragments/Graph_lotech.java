package com.ilab.user.e_lora.activities.fragments;


import Rest.ApiClient;
import Rest.ApiInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ilab.user.e_lora.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import model.HitsList;
import model.HitsObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class Graph_lotech extends Fragment {
    private static final String TAG = Graph_lotech.class.getSimpleName();

    GraphView graphView_t, graphView_h;
    ApiInterface apiInterface;

    ArrayList<Long> temp_value;
    ArrayList<Long> humidity_value;
    ArrayList<Date> time_value;


    public Graph_lotech() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_graph, container, false);
        get_most_recent_records(rootView);
        return rootView;
    }

    private void get_most_recent_records(final View view){
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

                graphView_t= view.findViewById(R.id.temp_graph);
                graphView_t.getViewport().setYAxisBoundsManual(true);
                graphView_t.getViewport().setMinY(getMinValue(temp_value) - 5);
                graphView_t.getViewport().setMaxY(getMaxValue(temp_value) + 5);
                graphView_t.getGridLabelRenderer().setNumHorizontalLabels(2);
                graphView_t.getGridLabelRenderer().setHorizontalAxisTitle("time");
                graphView_t.getGridLabelRenderer().setVerticalAxisTitle("temp(*c)");
                LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
                for ( int i = temp_value.size()-1; i>=0; i--){
                    Log.d(TAG, "TEMP " +hitsList.getData().get(i).getData_model().getPayload().getTemperature());
                    series.appendData(new DataPoint(time_value.get(i), temp_value.get(i)), true, temp_value.size());
                }

                graphView_t.addSeries(series);

                /**
                 * humidity graph
                 */
                graphView_h = view.findViewById(R.id.humidity_graph);
                graphView_h.getViewport().setYAxisBoundsManual(true);
                graphView_h.getViewport().setMaxY(getMaxValue(humidity_value) + 5);
                graphView_h.getViewport().setMinY(getMinValue(humidity_value) - 5);
                graphView_h.getGridLabelRenderer().setNumHorizontalLabels(2);
                graphView_h.getGridLabelRenderer().setVerticalAxisTitle("humidity(%)");
                graphView_h.getGridLabelRenderer().setHorizontalAxisTitle("time");
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

    /**
     * eget the minmum value from the arrylist
     * @param arrayList
     * @return
     */

    public long getMinValue(ArrayList<Long> arrayList){
        long minValue = arrayList.get(0);
        for (int i = 0; i<arrayList.size(); i++){
            if (arrayList.get(i) < minValue){
                minValue = arrayList.get(i+1);
            }else {
                minValue = arrayList.get(i);
            }
        }

        return minValue;


    }

    public long getMaxValue(ArrayList<Long> arrayList){
        long maxValue = arrayList.get(0);
        for (int i = 0; i < arrayList.size(); i++){
            if (arrayList.get(i) > maxValue){
                maxValue = arrayList.get(i);
            }else {
                maxValue = arrayList.get(i);
            }
        }
        return maxValue;
    }



}
