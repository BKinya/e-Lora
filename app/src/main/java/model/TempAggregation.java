package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TempAggregation {

    @SerializedName("aggregations")
    @Expose
    private Temperature temperature;



    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }


    public Temperature getTemperature() {
        return temperature;
    }


}
