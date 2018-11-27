package model;
/**
 * metadata class exposes 'time'field of metadata
 */

import com.google.gson.annotations.SerializedName;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Metadata {

    @SerializedName("time")
    private Date time;

    @SerializedName("gateways")
    private ArrayList<Gateway_fields> gateway_fields;


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public ArrayList<Gateway_fields> getGateway_fields() {
        return gateway_fields;
    }

    public void setGateway_fields(ArrayList<Gateway_fields> gateway_fields) {
        this.gateway_fields = gateway_fields;
    }
}
