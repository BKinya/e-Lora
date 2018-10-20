package Rest;

import model.HitsObject;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

/**
 * endpoint to query the rest api
 */

public interface ApiInterface {


    @GET("lotech/_search/?size=1&sort=metadata.time:desc")
    Call<HitsObject> get_last_record_l();

    @GET("telkom/_search/?size=1&sort=metadata.time:desc")
    Call<HitsObject>get_last_record_t();



}
