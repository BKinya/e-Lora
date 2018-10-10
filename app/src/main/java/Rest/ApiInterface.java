package Rest;

import model.HitsObject;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

/**
 * endpoint to query the rest api
 */

public interface ApiInterface {

    @GET("_search/?size=5&sort=metadata.time:desc")
    Call<HitsObject> get_most_recent_data();



}
