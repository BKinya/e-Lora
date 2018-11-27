package model;

import com.google.gson.annotations.SerializedName;

/**
 * class to expose specific fields of the payload
 * the class exposes metadata and payload_fields
 *
 */

public class DataFields {

    @SerializedName("payload_fields")
    private Payload_fields payload_fields;
    @SerializedName("metadata")
    private Metadata metadata;

    public void setPayload_fields(Payload_fields payload_fields) {
        this.payload_fields = payload_fields;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Payload_fields getPayload_fields() {
        return payload_fields;
    }

    public Metadata getMetadata() {
        return metadata;
    }
}
