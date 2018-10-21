package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HumidityAggregation {

    @SerializedName("aggregations")
    @Expose
    private Humidity humidity;


    public void setHumidity(Humidity humidity) {
        this.humidity = humidity;
    }

    public Humidity getHumidity() {
        return humidity;
    }
}
