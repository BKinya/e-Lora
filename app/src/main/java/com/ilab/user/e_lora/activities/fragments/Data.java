package com.ilab.user.e_lora.activities.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ilab.user.e_lora.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Data extends Fragment {


    public Data() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("Beatrice", "KINYA");
        return inflater.inflate(R.layout.fragment_data, container, false);
    }

}
