package com.dega.ibashi.infrastructure.schedulers;

import android.support.annotation.NonNull;

import rx.Scheduler;


/**
 * This interface is used to replace AndroidSchedulers for RxJava
 * since it is not available on JUnit test environment.
 *
 * Created by davedega on 06/04/18.
 */

public interface BaseSchedulerProvider {

    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler ui();
}
