package com.dega.ibashi;

import com.dega.ibashi.model.IbashiModel;

/**
 * Created by davedega on 06/04/18.
 */

public interface IbashiContract {
    interface Presenter {
        void loadTimeTable();
    }

    interface View {

        void setPresenter(Presenter presenter);

        void showErrorMessage(int string);

        void showDepartures(IbashiModel ibashiModel);

        void showLastUpdateTime();

    }
}
