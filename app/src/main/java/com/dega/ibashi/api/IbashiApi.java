package com.dega.ibashi.api;

import com.dega.ibashi.model.IbashiResponse;

import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

/**
 * Created by davedega on 06/04/18.
 */

public interface IbashiApi {
    @GET("/mobile/v1/network/station/10/timetable")
    Observable<IbashiResponse> loadTimetable(@Header("X-Api-Authentication") String token);
}