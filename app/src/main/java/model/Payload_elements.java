package model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Payload_elements {

    @SerializedName("temperature")
    private long temperature;
    @SerializedName("altitude")
    private long altitude;
    @SerializedName("humidity")
    private long humidity;
    @SerializedName("lux")
    private long lux;
    @SerializedName("soilMoisture")
    private long soil_moisture;
    @SerializedName("pressure")
    private long pressure;

    private Date time;

    public Payload_elements(long temperature, long altitude, long humidity, long lux, long soil_moisture, long pressure, Date time) {
        this.temperature = temperature;
        this.altitude = altitude;
        this.humidity = humidity;
        this.lux = lux;
        this.soil_moisture = soil_moisture;
        this.pressure = pressure;
        this.time = time;
    }

    public void setTemperature(long temperature) {
        this.temperature = temperature;
    }

    public void setAltitude(long altitude) {
        this.altitude = altitude;
    }

    public void setHumidy(long humidityy) {
        this.humidity = humidity;
    }

    public void setLux(long lux) {
        this.lux = lux;
    }

    public void setSoil_moisture(long soil_moisture) {
        this.soil_moisture = soil_moisture;
    }

    public void setPressure(long pressure) {
        this.pressure = pressure;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public long getTemperature() {
        return temperature;
    }

    public long getAltitude() {
        return altitude;
    }

    public long getHumidityy() {
        return humidity;
    }

    public long getLux() {
        return lux;
    }

    public long getSoil_moisture() {
        return soil_moisture;
    }

    public long getPressure() {
        return pressure;
    }

    public Date getTime() {
        return time;
    }
}
