
package com.dega.ibashi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Timetable {

    @SerializedName("arrivalDepartures")
    @Expose
    private List<ArrivalDeparture> arrivalDepartures = null;
    @SerializedName("message")
    @Expose
    private String message;

    public List<ArrivalDeparture> getArrivalDepartures() {
        return arrivalDepartures;
    }

    public void setArrivalDepartures(List<ArrivalDeparture> arrivalDepartures) {
        this.arrivalDepartures = arrivalDepartures;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
