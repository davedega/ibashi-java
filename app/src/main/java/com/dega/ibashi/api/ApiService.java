package com.dega.ibashi.api;

import com.dega.ibashi.model.IbashiResponse;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by davedega on 06/04/18.
 */

public class ApiService implements IbashiApi {
    private final IbashiApi api;

    public ApiService(Retrofit retrofit) {
        this.api = retrofit.create(IbashiApi.class);
    }

    @Override
    public Observable<IbashiResponse> loadTimetable(String token) {
        return api.loadTimetable(token);
    }
}
