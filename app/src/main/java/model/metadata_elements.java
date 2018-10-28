package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class metadata_elements {
    @SerializedName("time")
    private Date time;


    @SerializedName("gateways")
    private ArrayList<Gateway> gateways;

    public ArrayList<Gateway> getGateways() {
        return gateways;
    }

    public void setGateways(ArrayList<Gateway> gateways) {
        this.gateways = gateways;
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
