package com.dega.ibashi;

import com.dega.ibashi.api.ApiService;
import com.dega.ibashi.infrastructure.schedulers.BaseSchedulerProvider;
import com.dega.ibashi.infrastructure.schedulers.ImmediateSchedulerProvider;
import com.dega.ibashi.model.Constants;
import com.dega.ibashi.model.Datetime;
import com.dega.ibashi.model.Departure;
import com.dega.ibashi.model.IbashiResponse;
import com.dega.ibashi.model.Timetable;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.mockito.Mockito.when;

/**
 * The purpose of class is to test the following behavior:
 * <p>
 * 1. Given an empty timetable, show no departures screen
 * 2. Given a filled timetable, show departures in a list
 * 3. Inform the user when there is not internet connection
 * <p>
 * Created by davedega on 06/04/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class IbashiPresenterTest {

    @Mock
    private ApiService apiService;
    @Mock
    private IbashiContract.View mView;

    private InOrder inOrder;

    private IbashiPresenter presenter;
    private IbashiResponse emptyResponse;
    private IbashiResponse validResponse;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        inOrder = Mockito.inOrder(mView);
        BaseSchedulerProvider mSchedulerProvider = new ImmediateSchedulerProvider();

        presenter = new IbashiPresenter(apiService, mSchedulerProvider, mView);

        emptyResponse = new IbashiResponse();
        validResponse = new IbashiResponse();

        Timetable emptyTimeTable = new Timetable();
        Timetable filledTimeTable = new Timetable();

        List<Departure> departures = new ArrayList<>();
        departures.add(new Departure(new Datetime(1523195400, "GMT+02:00"), "L900", "Fráncfort (estación)"));
        departures.add(new Departure(new Datetime(1523196000, "GMT+02:00"), "L006", "Berlín (estación)"));
        departures.add(new Departure(new Datetime(1523196000, "GMT+06:00"), "L040", "Múnich (MUC aeropuerto)"));
        departures.add(new Departure(new Datetime(1523196000, "GMT+02:00"), "L040", "Innsbruck"));

        filledTimeTable.setArrivalDepartures(departures);
        validResponse.setTimetable(filledTimeTable);
        emptyResponse.setTimetable(emptyTimeTable);

    }

    /**
     * 1. Given an empty timetable, show no departures screen
     ***/
    @Test
    public void showEmptyDeparturesScreen() {
        when(apiService.loadTimetable(Constants.TOKEN))
                .thenReturn(rx.Observable.just(emptyResponse));
        presenter.loadTimeTable();
        inOrder.verify(mView).showEmptyList();
    }

    /**
     * 2. Given a filled timetable, show departures in a list and last update time
     ***/
    @Test
    public void showDepartures() {
        when(apiService.loadTimetable(Constants.TOKEN))
                .thenReturn(rx.Observable.just(validResponse));

        presenter.loadTimeTable();

        inOrder.verify(mView).showDepartures(validResponse.getTimetable().getArrivalDepartures());
        inOrder.verify(mView).showLastUpdateTime();
    }

    /**
     * 3. Inform the user when there is not internet connection
     ***/
    @Test
    public void informConnectionLost() {
        when(apiService.loadTimetable(Constants.TOKEN))
                .thenReturn(Observable.<IbashiResponse>error(new UnknownHostException("No internet!")));
        presenter.loadTimeTable();
        inOrder.verify(mView).showErrorMessage(R.string.no_internet_connection);
    }
}