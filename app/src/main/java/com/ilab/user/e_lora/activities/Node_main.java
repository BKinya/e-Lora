package com.ilab.user.e_lora.activities;

import Rest.ApiInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.ilab.user.e_lora.R;

public class Node_main extends AppCompatActivity {


    private static final String TAG = Node_main.class.getSimpleName();

    ArrayAdapter<CharSequence> adapter;
    String[] node_items = null;
    //views
    private ListView node_listView;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node_main);

        node_items = getResources().getStringArray(R.array.nodes_elements);
        adapter = new ArrayAdapter<CharSequence>(this, R.layout.list_item, node_items);

        node_listView = findViewById(R.id.nodes_listview);
        node_listView.setAdapter(adapter);

        node_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(Node_main.this, Nodes_data_charts.class));
            }
        });


    }


}


