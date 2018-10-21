package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Temperature {
    @SerializedName("avg_temp")
    @Expose
    private Avg_value avg_temp;

    public Avg_value getAvg_temp() {
        return avg_temp;
    }

    public void setAvg_temp(Avg_value avg_temp) {
        this.avg_temp = avg_temp;
    }
}
