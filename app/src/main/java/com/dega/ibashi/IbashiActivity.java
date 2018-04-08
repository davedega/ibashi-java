package com.dega.ibashi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dega.ibashi.api.ApiService;
import com.dega.ibashi.infrastructure.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;

public class IbashiActivity extends AppCompatActivity {
    @Inject
    ApiService apiService;

    @Inject
    BaseSchedulerProvider baseSchedulerProvider;

    private IbashiContract.View view;
    private IbashiContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ibashi);
        App.getComponent().inject(this);
        view = (IbashiFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        presenter = new IbashiPresenter(apiService, baseSchedulerProvider, view);
        view.setPresenter(presenter);
        presenter.loadTimeTable();

    }
}
