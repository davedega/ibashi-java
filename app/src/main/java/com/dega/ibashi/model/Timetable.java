
package com.dega.ibashi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Timetable {

    @SerializedName("departures")
    @Expose
    private List<Departure> arrivalDepartures = null;
    @SerializedName("message")
    @Expose
    private String message;

    public List<Departure> getArrivalDepartures() {
        return arrivalDepartures;
    }

    public void setArrivalDepartures(List<Departure> arrivalDepartures) {
        this.arrivalDepartures = arrivalDepartures;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
