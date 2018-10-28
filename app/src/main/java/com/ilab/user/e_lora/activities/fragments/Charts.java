package com.ilab.user.e_lora.activities.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ilab.user.e_lora.R;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import model.HitsList;
import utils.PayLoadResponses;
import utils.PayLoadResponsesCallbacks;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class Charts extends Fragment {

    private static  final String TAG = Charts.class.getSimpleName();

    PayLoadResponses payLoadResponses;

    //graphview
    private GraphView graphView_t, graphView_h;


    ArrayList<Long> temp_value;
    ArrayList<Long> humidity_value;
    ArrayList<Date> time_value;

    //simpleDateFormatter
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");


    public Charts() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_charts, container, false);

        graphView_t = rootView.findViewById(R.id.temp_graph);
        graphView_h = rootView.findViewById(R.id.humidity_graph);
        drawGraphs("lotech", rootView);

        return rootView;
    }

    public void drawGraphs(String index, View view) {
        payLoadResponses = new PayLoadResponses();
        payLoadResponses.get_most_recent_documents(index, new PayLoadResponsesCallbacks() {
            @Override
            public void onSuccess(HitsList hitsList) {
//
                temp_value = new ArrayList<Long>();
                humidity_value = new ArrayList<Long>();
                time_value = new ArrayList<Date>();
                for (int i = 0; i < hitsList.getData().size(); i++) {
                    time_value.add(hitsList.getData().get(i).getData_model().getMetadata().getTime());
                    temp_value.add(hitsList.getData().get(i).getData_model().getPayload().getTemperature());
                    humidity_value.add(hitsList.getData().get(i).getData_model().getPayload().getTemperature());


                }



                //temperature graph
                graphView_t.getViewport().setYAxisBoundsManual(true);
                graphView_t.getViewport().setMinY(getMinValue(temp_value) - 5);
                graphView_t.getViewport().setMaxY(getMaxValue(temp_value) + 5);
                graphView_t.getGridLabelRenderer().setVerticalAxisTitle("temp(*c)");
//                graphView_t.getViewport().setScalable(true);
//                graphView_t.getViewport().setScalableY(true);
                LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
                for (int i = temp_value.size() - 1; i >= 0; i--) {
                    Log.d(TAG, "TEMP " + hitsList.getData().get(i).getData_model().getPayload().getTemperature());
                    series.appendData(new DataPoint(time_value.get(i), temp_value.get(i)), true, temp_value.size());
                }

                graphView_t.addSeries(series);
                // graphView_t.getViewport().setScalable(true);
                graphView_t.getViewport().setScrollable(true);
                //graphView_t.getViewport().setScalableY(true);
                graphView_t.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                    @Override
                    public String formatLabel(double value, boolean isValueX) {
                        if (isValueX) {
                            return simpleDateFormat.format(new Date((long) value));
                        } else {
                            return super.formatLabel(value, isValueX);
                        }
                    }
                });
//
//                /**
//                 * humidity graph
//                 */
//
                graphView_h.getViewport().setYAxisBoundsManual(true);
                graphView_h.getViewport().setMaxY(getMaxValue(humidity_value) + 5);
                graphView_h.getViewport().setMinY(getMinValue(humidity_value) - 5);
                graphView_h.getViewport().setScrollable(true);
//                graphView_h.getViewport().setScalable(true);
//                graphView_h.getViewport().setScalableY(true);

                graphView_h.getGridLabelRenderer().setVerticalAxisTitle("humidity(%)");
                LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>();
                for (int j = humidity_value.size()-1; j>=0; j--){

                    series1.appendData(new DataPoint(time_value.get(j), humidity_value.get(j)), true, humidity_value.size());

                    Log.d(TAG,"HUMIDITY "+hitsList.getData().get(j).getData_model().getPayload().getHumidityy());
                    Log.d(TAG, "TIME"+hitsList.getData().get(j).getData_model().getMetadata().getTime());
                }
                graphView_h.addSeries(series1);
                graphView_h.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
                    @Override
                    public String formatLabel(double value, boolean isValueX) {
                        if (isValueX){
                            return simpleDateFormat.format(new Date((long) value));
                        }else {
                            return super.formatLabel(value, isValueX);
                        }
                    }
                });
//
//
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });

    }

    /**
     * //     * get the minmum value from the arrylist
     * //     * @param arrayList
     * //     * @return
     * //
     */

    public long getMinValue(ArrayList<Long> arrayList) {
        long minValue = arrayList.get(0);
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) < minValue) {
                minValue = arrayList.get(i + 1);
            } else {
                minValue = arrayList.get(i);
            }
        }

        return minValue;


    }

    public long getMaxValue(ArrayList<Long> arrayList) {
        long maxValue = arrayList.get(0);
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) > maxValue) {
                maxValue = arrayList.get(i);
            } else {
                maxValue = arrayList.get(i);
            }
        }
        return maxValue;
    }
}
