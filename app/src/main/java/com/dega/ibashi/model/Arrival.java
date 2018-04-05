
package com.dega.ibashi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Arrival {

    @SerializedName("through_the_stations")
    @Expose
    private String throughTheStations;
    @SerializedName("datetime")
    @Expose
    private Datetime datetime;
    @SerializedName("line_direction")
    @Expose
    private String lineDirection;
    @SerializedName("route")
    @Expose
    private List<Route> route = null;
    @SerializedName("ride_id")
    @Expose
    private Integer rideId;
    @SerializedName("trip_uid")
    @Expose
    private String tripUid;
    @SerializedName("line_code")
    @Expose
    private String lineCode;
    @SerializedName("direction")
    @Expose
    private String direction;

    public String getThroughTheStations() {
        return throughTheStations;
    }

    public void setThroughTheStations(String throughTheStations) {
        this.throughTheStations = throughTheStations;
    }

    public Datetime getDatetime() {
        return datetime;
    }

    public void setDatetime(Datetime datetime) {
        this.datetime = datetime;
    }

    public String getLineDirection() {
        return lineDirection;
    }

    public void setLineDirection(String lineDirection) {
        this.lineDirection = lineDirection;
    }

    public List<Route> getRoute() {
        return route;
    }

    public void setRoute(List<Route> route) {
        this.route = route;
    }

    public Integer getRideId() {
        return rideId;
    }

    public void setRideId(Integer rideId) {
        this.rideId = rideId;
    }

    public String getTripUid() {
        return tripUid;
    }

    public void setTripUid(String tripUid) {
        this.tripUid = tripUid;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

}
