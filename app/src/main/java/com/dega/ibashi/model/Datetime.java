
package com.dega.ibashi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datetime {

    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;
    @SerializedName("tz")
    @Expose
    private String tz;

    public Datetime(Integer timestamp, String tz) {
        this.timestamp = timestamp;
        this.tz = tz;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

}
