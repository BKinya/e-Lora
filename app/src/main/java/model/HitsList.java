package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HitsList {

    @SerializedName("hits")
    @Expose
    private List<DataSource> data;


    public List<DataSource> getData() {
        return data;
    }

    public void setData(List<DataSource> data) {
        this.data = data;
    }
}
