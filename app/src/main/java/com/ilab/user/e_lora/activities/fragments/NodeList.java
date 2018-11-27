package com.ilab.user.e_lora.activities.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.ilab.user.e_lora.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NodeList extends ListFragment  implements AdapterView.OnItemClickListener {




    public NodeList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_node_list, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.nodes_elements, R.layout.node_list_item);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String node_selected = getListView().getItemAtPosition(position).toString();
            if (node_selected.equals("Ilab Node")){
                addFragment(new Node());

            }else if (node_selected.equals("Telkom Node")){
                addFragment(new Dashboard());
            }
    }

    public void addFragment(Fragment fragment){
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmment_fragmnent, fragment);
        transaction.commit();


    }
}
