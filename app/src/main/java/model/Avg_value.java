package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Avg_value {
    @SerializedName("value")
    @Expose
    private double value;


    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
