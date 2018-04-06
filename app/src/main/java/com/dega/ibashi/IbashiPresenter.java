package com.dega.ibashi;

import android.content.Context;
import android.util.Log;

/**
 * Created by davedega on 06/04/18.
 */

public class IbashiPresenter implements IbashiContract.Presenter {

    private IbashiContract.View view;
    private Context context;

    IbashiPresenter(IbashiContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void loadTimeTable() {
        Log.e("LOLO< :)","POCK YIU!");
    }
}
