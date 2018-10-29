package com.ilab.user.e_lora.activities.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import com.ilab.user.e_lora.R;
import com.ilab.user.e_lora.activities.Node_main;
import com.ilab.user.e_lora.activities.Nodes_data_charts;
import model.Gateway;
import model.HitsList;
import utils.PayLoadResponses;
import utils.PayLoadResponsesCallbacks;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

/**
 * A simple {@link Fragment} subclass.
 */
public class Node extends Fragment {

    private static final String TAG = Node.class.getSimpleName();

    PayLoadResponses payLoadResponses;
    HitsList hitsList;


    //views
    private TextView location_label_txtview, location_value_txtview, last_seen_txtview, gateway_id_txtxview, signal_level_txtview;

    View rootView;

    String node_select;
    private Bundle bundle;

    Nodes_data_charts nodes_data_charts;
    public Node() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_node, container, false);


        return rootView;
    }

    /**
     * String dateTxt;
     *     private Bundle bundle;
     *
     *     //within OncreateView
     *      bundle = this.getArguments();
     *         dateTxt = bundle.getString("DATE");
     *         Log.e("DATE_FRAG_INBOUND",""+dateTxt);
     */
    @Override
    public void onResume() {
        super.onResume();


        bundle = this.getArguments();
        node_select = bundle.getString("node");
        if (node_select.equals("Node 1")){
            receive_payload("lotech", rootView);
        }else if (node_select.equals("Node 2")){
            receive_payload("telkom", rootView);
        }

    }

    /**
     * receive payload and display to the user
     *
     */
    public void receive_payload(String index, final View view) {
        payLoadResponses = new PayLoadResponses();
        payLoadResponses.get_most_recent_documents(index, new PayLoadResponsesCallbacks() {
            @Override
            public void onSuccess(HitsList hitsList) {
                long temperature = hitsList.getData().get(0).getData_model().getPayload().getTemperature();
                getViews(view);



//                location_label_txtview.setVisibility(View.GONE);
//                location_value_txtview.setVisibility(View.GONE);

                /**
                 * convert date to string
                 */
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String date = simpleDateFormat.format(hitsList.getData().get(0).getData_model().getMetadata().getTime());
                last_seen_txtview.setText(date);

                //Gateway
                ArrayList<Gateway> gateways = hitsList.getData().get(0).getData_model().getMetadata().getGateways();
                String gateway_id = gateways.get(0).getGateway_id();
                String signal_level = new Long(gateways.get(0).getRssi()).toString();


                gateway_id_txtxview.setText(gateway_id);
                signal_level_txtview.setText(signal_level);




            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.e(TAG, "ERRORT "+throwable.getMessage());
            }
        });
    }

    public void getViews(View view){
//        location_label_txtview = view.findViewById(R.id.location_label_txtview);
//        location_value_txtview = view.findViewById(R.id.location_value_txtview);
        last_seen_txtview = view.findViewById(R.id.last_seen_value_txtview);
        gateway_id_txtxview = view.findViewById(R.id.gateway_id_value_txtview);
        signal_level_txtview = view.findViewById(R.id.signal_level_value_txtview);
    }

}
