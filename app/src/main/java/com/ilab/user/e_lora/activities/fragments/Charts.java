package com.ilab.user.e_lora.activities.fragments;


import Rest.ApiClient;
import Rest.ApiInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import com.ilab.user.e_lora.R;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import model.DataFields;
import model.DataModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class Charts extends Fragment {

    private static final String TAG = Charts.class.getSimpleName();

    //graphview
    private GraphView graphView_t, graphView_h;
    View rootView;

    ApiInterface apiInterface;

    private ArrayList<DataFields> datalist = new ArrayList<>();
    private ArrayList<Long> temp_value = new ArrayList<>();
    private ArrayList<Long> humidity_value = new ArrayList<>();
    private ArrayList<Date> time_value = new ArrayList<>();

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");


    //linegraph series
    private LineGraphSeries<DataPoint> series;

    public Charts() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_charts, container, false);

        graphView_t = rootView.findViewById(R.id.temp_graph);
        graphView_h = rootView.findViewById(R.id.humidity_graph);

        getPayloadData();

        return rootView;
    }

    /**
     * get data from the API
     * use the data to plot graphs: temperature graph and humidity graph
     */
    private void getPayloadData() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<DataModel> call = apiInterface.getPayLoadData();
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                datalist = response.body().getPayloads();
                for (int i = 0; i < datalist.size(); i++) {
                    temp_value.add(datalist.get(i).getPayload_fields().getTemperature());
                    humidity_value.add(datalist.get(i).getPayload_fields().getHumidity());
                    time_value.add(datalist.get(i).getMetadata().getTime());

                }

                //temperature graph
                drawGraphs(graphView_t, time_value, temp_value);

                //humidity graph
                drawGraphs(graphView_h, time_value, humidity_value);


            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Log.e(TAG, "ERROR" + t.getMessage());
            }
        });


    }

    /**
     * Draw a graph
     *
     * @param graphView
     * @param Y_axis
     * @param X_axis
     */

    public void drawGraphs(GraphView graphView, ArrayList<Date> X_axis, ArrayList<Long> Y_axis) {

        //set minimum and maximum bound for x axis
        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinY(getMinValue(Y_axis) - 3);
        graphView.getViewport().setMaxY(getMaxValue(Y_axis) + 3);

        //set y_axis label
        //graphView.getGridLabelRenderer().setVerticalAxisTitle(y_axis_label);

        //draw the graph
        series = new LineGraphSeries<>();
        for (int i = Y_axis.size() - 1; i >= 0; i--) {
            series.appendData(new DataPoint(X_axis.get(i), Y_axis.get(i)), true, X_axis.size());
        }

        graphView.addSeries(series);
        graphView.getViewport().setScrollable(true);
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    return simpleDateFormat.format(new Date((long) value));
                } else {
                    return super.formatLabel(value, isValueX);
                }
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

    //
    private DataPoint[] generateDataPoints(ArrayList<Date> x_axis, ArrayList<Long> y_axis) {
        int count = x_axis.size();
        DataPoint[] dataPoints = new DataPoint[count];
        for (int i = 0; i < count; i++) {
            Date x = x_axis.get(i);
            long y = y_axis.get(i);
            DataPoint d = new DataPoint(x, y);
            dataPoints[i] = d;
        }
        return dataPoints;
    }
}
