package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataSource {

    @SerializedName("_source")
    @Expose
    private Data_model data_model;

    public Data_model getData_model() {
        return data_model;
    }

    public void setData_model(Data_model data_model) {
        this.data_model = data_model;
    }
}
