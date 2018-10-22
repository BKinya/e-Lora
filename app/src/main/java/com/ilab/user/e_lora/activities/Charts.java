package com.ilab.user.e_lora.activities;

import Rest.ApiClient;
import Rest.ApiInterface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.ilab.user.e_lora.R;
import com.ilab.user.e_lora.activities.fragments.Graph_lotech;
import com.ilab.user.e_lora.activities.fragments.Graph_telkom;
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

        Bundle bundle = getIntent().getExtras();
        String selected_node = bundle.getString("Node");


        addfragment(selected_node);




    }


    //add fragment
    private void addfragment(String node){
        switch (node){
            case "Node 1":
                Log.d(TAG, "NODE 1" +  node);
                Graph_lotech graph_lotech = new Graph_lotech();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.graph_framelayout, graph_lotech);
                fragmentTransaction.commit();
                break;
            case "Node 2" :
                Log.d(TAG, "NODE 2" +  node);
                Graph_telkom graph_telkom = new Graph_telkom();
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                fragmentTransaction1.replace(R.id.graph_framelayout, graph_telkom);
                fragmentTransaction1.commit();
                break;
            default:
                break;

        }


    }


}
