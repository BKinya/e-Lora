package model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class metadata_elements {
    @SerializedName("time")
    private Date time;

    @SerializedName("frequency")
    private float frequency;

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

    public float getFrequency() {
        return frequency;
    }

    public metadata_elements(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
