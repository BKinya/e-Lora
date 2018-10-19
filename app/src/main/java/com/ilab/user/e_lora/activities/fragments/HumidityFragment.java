package com.ilab.user.e_lora.activities.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class HumidityFragment extends Fragment {

    GraphView graphView;


    public HumidityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_humidity, container, false);
        drawGraph(rootView, graphView);
        return rootView;
    }

    //draw the temperature graph
    private void drawGraph(View view, GraphView graph) {
        graph = view.findViewById(R.id.humidity_graph);
//
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 2),
                new DataPoint(1, 3),
                new DataPoint(2, 5),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });

//
       graph.addSeries(series);
    }

}
