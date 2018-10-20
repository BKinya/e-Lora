package Rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * retrofit builder class
 */

public class ApiClient {

    //base url
    private static final String BASE_URL = "https://search-iot-data-analytics-okmamccstll4hvuv6gqulzqz2u.us-east-1.es.amazonaws.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
