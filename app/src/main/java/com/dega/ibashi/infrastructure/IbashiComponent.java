package com.dega.ibashi.infrastructure;

import com.dega.ibashi.IbashiActivity;
import com.dega.ibashi.IbashiPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by davedega on 06/04/18.
 */

@Singleton
@Component(modules = IbashiModule.class)
public interface IbashiComponent {

    void inject(IbashiActivity activity);

}


