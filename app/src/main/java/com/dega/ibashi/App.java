package com.dega.ibashi;

import android.app.Application;

import com.dega.ibashi.infrastructure.DaggerIbashiComponent;
import com.dega.ibashi.infrastructure.IbashiComponent;
import com.dega.ibashi.infrastructure.IbashiModule;

/**
 * Created by davedega on 06/04/18.
 */

public class App extends Application {

    static IbashiComponent component;

    static IbashiComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerIbashiComponent.builder().ibashiModule(new IbashiModule(this)).build();
    }
}
