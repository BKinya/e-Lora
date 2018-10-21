package Rest;

import model.Humidity;
import model.HumidityAggregation;
import model.TempAggregation;
import model.HitsObject;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * endpoint to query the rest api
 */

public interface ApiInterface {

    //TODO use parameterized queries to specify index and value you are aggregating


    @GET("lotech/_search/?size=1&sort=metadata.time:desc")
    Call<HitsObject> get_last_record_l();

    @GET("telkom/_search/?size=1&sort=metadata.time:desc")
    Call<HitsObject>get_last_record_t();

    @GET("lotech/_search?size=0&source={\"aggs\":{\"avg_temp\":{\"avg\":{\"field\":\"payload.temperature\"}}}} &source_content_type=application/json")
    Call<TempAggregation>get_avg_temperature();

    @GET("lotech/_search?size=0&source={\"aggs\":{\"avg_humidity\":{\"avg\":{\"field\":\"payload.humidity\"}}}} &source_content_type=application/json")
    Call<HumidityAggregation>get_avg_humidity();

    //telkom node
    @GET("telkom/_search?size=0&source={\"aggs\":{\"avg_temp\":{\"avg\":{\"field\":\"payload.temperature\"}}}} &source_content_type=application/json")
    Call<TempAggregation>get_avg_temperature_t();

    @GET("telkom/_search?size=0&source={\"aggs\":{\"avg_humidity\":{\"avg\":{\"field\":\"payload.humidity\"}}}} &source_content_type=application/json")
    Call<HumidityAggregation>get_avg_humidity_t();

    @GET("lotech/_search?&&sort=metadata.time:desc&source=\n" +
            "{\"query\":{\"range\":{\"metadata.time\":{\"gte\":\"now-1h\", \"lte\":\"now\"}}}}&source_content_type=application/json")
    Call<HitsObject>get_most_recent_records_l();





}
