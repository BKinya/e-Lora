package com.ilab.user.e_lora.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.ilab.user.e_lora.R;
import com.ilab.user.e_lora.activities.fragments.HumidityFragment;
import com.ilab.user.e_lora.activities.fragments.TempGraphFragment;

public class Charts extends AppCompatActivity {

    String [] nodes_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);


        addfragment();



    }


    //add fragment
    private void addfragment(){
        TempGraphFragment tempGraphFragment = new TempGraphFragment();
        HumidityFragment humidityFragment = new HumidityFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.charts_fragment1, tempGraphFragment);
        fragmentTransaction.add(R.id.charts_fragment2, humidityFragment );
        fragmentTransaction.commit();

    }
}
