
package com.dega.ibashi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Timetable {

    @SerializedName("arrivals")
    @Expose
    private List<Arrival> arrivals = null;
    @SerializedName("message")
    @Expose
    private String message;

    public List<Arrival> getArrivals() {
        return arrivals;
    }

    public void setArrivals(List<Arrival> arrivals) {
        this.arrivals = arrivals;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
