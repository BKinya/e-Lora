package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Humidity {

    @SerializedName("avg_humidity")
    @Expose
    private Avg_value avg_humidity;

    public void setAvg_humidity(Avg_value avg_humidity) {
        this.avg_humidity = avg_humidity;
    }

    public Avg_value getAvg_humidity() {
        return avg_humidity;
    }
}
