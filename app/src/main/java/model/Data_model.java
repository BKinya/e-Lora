package model;

import com.google.gson.annotations.SerializedName;


public class Data_model {

    //data variables
    @SerializedName("payload")
    private Payload_elements payload;

    @SerializedName("metadata")
    private metadata_elements metadata;

    public Data_model(Payload_elements payload, metadata_elements metadata) {
        this.payload = payload;
        this.metadata = metadata;
    }

    public void setPayload(Payload_elements payload) {
        this.payload = payload;
    }

    public void setMetadata(metadata_elements metadata) {
        this.metadata = metadata;
    }

    public Payload_elements getPayload() {
        return payload;
    }

    public metadata_elements getMetadata() {
        return metadata;
    }
}
