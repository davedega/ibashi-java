package com.dega.ibashi;

import com.dega.ibashi.api.ApiService;
import com.dega.ibashi.infrastructure.schedulers.BaseSchedulerProvider;
import com.dega.ibashi.model.Constants;
import com.dega.ibashi.model.Departure;
import com.dega.ibashi.model.IbashiResponse;

import java.net.UnknownHostException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Observer;

/**
 * Created by davedega on 06/04/18.
 */

public class IbashiPresenter implements IbashiContract.Presenter {

    @Inject
    ApiService apiService;

    // Replacement for AndroidSchedulers since it is not available on JUnit test environment
    @Inject
    BaseSchedulerProvider schedulerProvider;

    // Communication to the view through the contract
    private IbashiContract.View view;


    IbashiPresenter(IbashiContract.View view) {
        this.view = view;
        App.getComponent().inject(this);
    }


    @Override
    public void loadTimeTable() {

        Observable<IbashiResponse> ibashiResponse = apiService.loadTimetable(Constants.TOKEN);

        ibashiResponse
                .subscribeOn(schedulerProvider.computation())
                .observeOn(schedulerProvider.ui())
                .subscribe(new Observer<IbashiResponse>() {
                    @Override
                    public void onCompleted() {
                        view.showLastUpdateTime();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof UnknownHostException) {
                            view.showErrorMessage(R.string.no_internet_connection);
                        } else if (e instanceof HttpException) {
                            view.showErrorMessage(R.string.not_found);
                        } else {
                            view.showErrorMessage(R.string.expection_message);
                        }
                    }

                    @Override
                    public void onNext(IbashiResponse vehiclesResponse) {
                        List<Departure> departures = vehiclesResponse.getTimetable().getArrivalDepartures();
                        if (departures != null && departures.size() > 0)
                            view.showDepartures(vehiclesResponse.getTimetable().getArrivalDepartures());
                        else {
                            view.showEmptyList();
                        }
                    }
                });
    }
}
