package com.dega.ibashi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class IbashiActivity extends AppCompatActivity {

    private IbashiContract.View view;
    private IbashiContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ibashi);
        view = (IbashiFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        presenter = new IbashiPresenter(view);
        view.setPresenter(presenter);
        presenter.loadTimeTable();
    }
}
