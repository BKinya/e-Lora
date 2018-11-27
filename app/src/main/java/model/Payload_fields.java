package model;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Payload_field exposes payload_field items: humidity and temperature
 * pressure, lux, soilMoisture
 */

public class Payload_fields {

    @SerializedName("temperature")
    private long temperature;
    @SerializedName("humidity")
    private long humidity;
    @SerializedName("soilMoisture")
    private long soil_moisture;
    @SerializedName("pressure")
    private long pressure;
    @SerializedName("lux")
    private long lux;

    public void setSoil_moisture(long soil_moisture) {
        this.soil_moisture = soil_moisture;
    }

    public void setPressure(long pressure) {
        this.pressure = pressure;
    }

    public void setLux(long lux) {
        this.lux = lux;
    }

    public long getSoil_moisture() {
        return soil_moisture;
    }

    public long getPressure() {
        return pressure;
    }

    public long getLux() {
        return lux;
    }

    public long getTemperature() {
        return temperature;
    }

    public long getHumidity() {
        return humidity;
    }

    public void setTemperature(long temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(long humidity) {
        this.humidity = humidity;
    }
}
