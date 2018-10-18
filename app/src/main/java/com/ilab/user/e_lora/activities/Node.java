package com.ilab.user.e_lora.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.ilab.user.e_lora.R;

public class Node extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

   ArrayAdapter<CharSequence> adapter;
    //views
    private Spinner nodes_spinner;

    //TODO when a node is selected, let it replace the spinner, make the title/ name of the node visible

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node);
        nodes_spinner= findViewById(R.id.node_spinner);
        loadSpinner(nodes_spinner);


    }

    public void loadSpinner(Spinner spinner){


        /**
         * adapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item_text, items);
         * spinner.setAdapter(adapter);
         */

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.nodes_spinner_elements,
                android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //apply adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
