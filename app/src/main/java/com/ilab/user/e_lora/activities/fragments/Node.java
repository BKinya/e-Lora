package com.ilab.user.e_lora.activities.fragments;


import adapter.SectionPageAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ilab.user.e_lora.R;

public class Node extends Fragment {


    private SectionPageAdapter sectionPageAdapter;
    private ViewPager viewPager;


    public Node() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_node, container, false);
        getActivity().setTitle(getResources().getString(R.string.telkom_node));

        viewPager = rootView.findViewById(R.id.container);
        setUpViewPager(viewPager);

        TabLayout tabLayout = rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }

    private void setUpViewPager(ViewPager mViewpager){
        sectionPageAdapter = new SectionPageAdapter(getChildFragmentManager());
        sectionPageAdapter.addFragment(new DeviceInfo(), getResources().getString(R.string.device_title));
        sectionPageAdapter.addFragment(new Data(), getResources().getString(R.string.data_title));
        sectionPageAdapter.addFragment(new Charts(), getResources().getString(R.string.chart_title));
        viewPager.setAdapter(sectionPageAdapter);
    }



}
