package model;

import android.widget.ArrayAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataModel {

    @SerializedName("payloads")
    private ArrayList<DataFields> payloads = new ArrayList<>();

    public ArrayList<DataFields> getPayloads() {
        return payloads;
    }

    public void setPayloads(ArrayList<DataFields> payloads) {
        this.payloads = payloads;
    }
}
