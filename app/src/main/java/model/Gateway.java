package model;

import com.google.gson.annotations.SerializedName;

public class Gateway {

    @SerializedName("gtw_id")
    private String gateway_id;

    @SerializedName("rssi")
    private long rssi;

    public void setGateway_id(String gateway_id) {
        this.gateway_id = gateway_id;
    }

    public void setRssi(long rssi) {
        this.rssi = rssi;
    }

    public String getGateway_id() {
        return gateway_id;
    }

    public long getRssi() {
        return rssi;
    }
}
