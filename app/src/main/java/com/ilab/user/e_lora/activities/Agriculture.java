package com.ilab.user.e_lora.activities;

import Rest.ApiClient;
import Rest.ApiInterface;
import adapter.Data_adapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.ilab.user.e_lora.R;
import model.Data_model;
import model.HitsList;
import model.HitsObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Agriculture extends AppCompatActivity {

    private ArrayList<Data_model> data_models;

    private static final String TAG = Agriculture.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_argriculture);

        final RecyclerView recyclerView = findViewById(R.id.data_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        data_models = new ArrayList<>();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<HitsObject> call = apiInterface.get_most_recent_data();
        call.enqueue(new Callback<HitsObject>() {
            @Override
            public void onResponse(Call<HitsObject> call, Response<HitsObject> response) {
                HitsList hitsList = new HitsList();
                String jsonResponse ="";
                try{
                    Log.d(TAG, "onResponse: server response: " + response.toString());
                    if(response.isSuccessful()){
                        hitsList = response.body().getHits();
                    }else{
                        jsonResponse = response.errorBody().string();
                    }
                    Log.d(TAG, "onResponse: hits: " + hitsList);

                    for(int i = 0; i < hitsList.getData().size(); i++) {

                        data_models.add(hitsList.getData().get(i).getData_model());

                    }
                    recyclerView.setAdapter(new Data_adapter(data_models, R.layout.data_list_item, getApplicationContext()));
                    Log.d(TAG, "SIZE"+data_models.size());


                }catch (NullPointerException e){
                    Log.d(TAG, "onresponse: nullpointerexception " + e.getMessage());
                }catch (IndexOutOfBoundsException e){
                    Log.d(TAG, "onresponse: IndexOutOfBound"+e.getMessage());
                }catch (IOException e){
                    Log.d(TAG, "onreponse: IOEXCEPYION"+e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<HitsObject> call, Throwable t) {
                Log.d(TAG, "KINYA"+t.getMessage());
            }
        });

    }
}
