package com.dega.ibashi;

import com.dega.ibashi.api.ApiService;
import com.dega.ibashi.model.Departure;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

/**
 * The purpose of class is to test the following behavior:
 *
 *  1. Given an empty timetable, show no departures screen
 *  2. Given a filled timetable, show departures in a list
 *  3. Inform the user when there is not internet connection
 *
 * Created by davedega on 06/04/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class IbashiPresenterTest {

    @Mock
    private ApiService apiService;

    @Mock
    private IbashiContract.View mView;

    private IbashiPresenter presenter;
    private List<Departure> departures;

    @Before
    public void setup() {

    }

    /**
     * 1. Given an empty timetable, show no departures screen
     ***/
    @Test
    public void showEmptyDeparturesScreen() {


    }
    /**
     *  2. Given a filled timetable, show departures in a list
     ***/
    @Test
    public void showDepartures() {

    }

    /**
     *  3. Inform the user when there is not internet connection
     ***/
    @Test
    public void informConnectionLost() {

    }
}