package Rest;

import model.DataModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * endpoint to query the rest api
 */

public interface ApiInterface {

    @GET("payloads")
    Call<DataModel> getPayLoadData();
}
