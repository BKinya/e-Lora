package com.ilab.user.e_lora.activities.fragments;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class TempGraphFragment extends Fragment {
    private static final String TAG = TempGraphFragment.class.getSimpleName();

    GraphView graphView;

    int [] x = {5, 6, 7, 8, 9};
    int [] y = {2, 3, 9,7, 6};
    long [] temp;
    long [] time;


    public TempGraphFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_charts, container, false);
        drawGraph(rootview, graphView);


        if (getArguments() != null){
            temp = getArguments().getLongArray("temperature");
            Log.d(TAG, "TEMP " +temp[0]);

        }





        return rootview;

    }


    //draw the temperature graph
    private void drawGraph(View view, GraphView graph){
        graph = view.findViewById(R.id.temp_graph);
        DataPoint [] dp = new DataPoint[5];
        for ( int i =0; i<x.length; i++){
            dp[i] = new DataPoint(x[i], y[i]);

        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dp);

        graph.addSeries(series);
    }



}
