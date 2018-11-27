package model;

import com.google.gson.annotations.SerializedName;
import com.ilab.user.e_lora.activities.fragments.Dashboard;

import java.util.Date;

public class Gateway_fields {

    @SerializedName("rssi")
    private long rssi;
    @SerializedName("time")
    private Date time;
    @SerializedName("gtw_id")
    private String gateway_id;


    public void setRssi(long rssi) {
        this.rssi = rssi;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setGateway_id(String gateway_id) {
        this.gateway_id = gateway_id;
    }

    public long getRssi() {
        return rssi;
    }

    public Date getTime() {
        return time;
    }

    public String getGateway_id() {
        return gateway_id;
    }
}
