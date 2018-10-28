package utils;

import Rest.ApiClient;
import Rest.ApiInterface;
import android.util.Log;
import android.widget.TextView;
import model.HitsList;
import model.HitsObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



/**
 * has methods to execute endpoints and return payload data to be used in activity/fragment classes
 */
public class PayLoadResponses {

    private static final String TAG = "TAG";
    ApiInterface apiInterface;

    public void get_most_recent_documents(String index, final PayLoadResponsesCallbacks callbacks){
        /**TODO returning the payload
         * But BlockingQueue is synchronous
         * rxjava hope will save
         */
        //final BlockingQueue<HitsList> blockingQueue = new ArrayBlockingQueue<>(1);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<HitsObject> call = apiInterface.get_most_recent_documents(index);
        call.enqueue(new Callback<HitsObject>() {
            @Override
            public void onResponse(Call<HitsObject> call, Response<HitsObject> response) {
                HitsList hitsList = new HitsList();
                String jsonResponse = " ";
                if (response.isSuccessful()){
                    try {
                        if (response.isSuccessful()){
                            hitsList = response.body().getHits();
                            if (callbacks!=null){
                                callbacks.onSuccess(hitsList);
                            }


                        }else{
                            jsonResponse = response.errorBody().toString();
                        }



                    }catch (NullPointerException e){
                        Log.e(TAG, "NULLPOINTER "+e.getMessage());

                    }catch (IndexOutOfBoundsException e){
                        Log.e(TAG, "INDEXOUTOFBOUND "+e.getMessage() );

                    }
                }
            }

            @Override
            public void onFailure(Call<HitsObject> call, Throwable t) {
                    Log.e(TAG, "ERROR "+t.getMessage());
                    if (callbacks != null){
                        callbacks.onFailure(t);

                    }
            }
        });

    }

}
