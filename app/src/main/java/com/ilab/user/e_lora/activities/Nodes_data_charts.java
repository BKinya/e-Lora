package com.ilab.user.e_lora.activities;

import adapter.SectionPageAdapter;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import android.util.Log;
import com.ilab.user.e_lora.R;
import com.ilab.user.e_lora.activities.fragments.Charts;
import com.ilab.user.e_lora.activities.fragments.Data;
import com.ilab.user.e_lora.activities.fragments.Node;

public class Nodes_data_charts extends AppCompatActivity {


    private SectionPageAdapter sectionPageAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nodes_data_charts);

        sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    public  String receiveSelectedNo(){
        String node_selected = getIntent().getStringExtra("node");
        return  node_selected;
    }


    private void setViewPager(ViewPager viewPager){

        Bundle bundle = new Bundle();
        bundle.putString("node", receiveSelectedNo());

        Node node = new Node();
        node.setArguments(bundle);

        Data data = new Data();
        data.setArguments(bundle);

        Charts charts = new Charts();
        charts.setArguments(bundle);


        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(node, "Node");
        adapter.addFragment(data, "Data");
        adapter.addFragment(charts, "Charts");
        viewPager.setAdapter(adapter);





    }


}
