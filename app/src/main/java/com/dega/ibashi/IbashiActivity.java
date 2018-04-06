package com.dega.ibashi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class IbashiActivity extends AppCompatActivity {


    IbashiContract.View view;
    IbashiContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ibashi);
        view = (IbashiFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        presenter = new IbashiPresenter(view, this);
        view.setPresenter(presenter);
        presenter.loadTimeTable();
    }
}
