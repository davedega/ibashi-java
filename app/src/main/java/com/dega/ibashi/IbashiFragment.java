package com.dega.ibashi;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dega.ibashi.model.ArrivalDeparture;
import com.dega.ibashi.model.IbashiModel;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class IbashiFragment extends Fragment implements IbashiContract.View {

    IbashiContract.Presenter presenter;
    RecyclerView timeTableRV;
    ImageView logoIV;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ibashi, container, false);
        timeTableRV = rootView.findViewById(R.id.timeTableRV);
        logoIV = rootView.findViewById(R.id.logoIV);
        return rootView;
    }

    @Override
    public void setPresenter(IbashiContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showErrorMessage(int string) {
        logoIV.setVisibility(View.VISIBLE);
        timeTableRV.setVisibility(View.GONE);
        Snackbar mySnackbar = Snackbar.make(logoIV,
                getString(string), Snackbar.LENGTH_SHORT);
        mySnackbar.setAction(R.string.try_again, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loadTimeTable();
            }
        });

        mySnackbar.show();
    }

    @Override
    public void showDepartures(IbashiModel ibashiModel) {
        timeTableRV.setVisibility(View.VISIBLE);
        logoIV.setVisibility(View.GONE);

        LinearLayoutManager mLayoutManager = new
                LinearLayoutManager(getActivity().getApplicationContext());
        timeTableRV.setLayoutManager(mLayoutManager);

        TimetableAdapter adapterTimeTable = new
                TimetableAdapter(new ArrayList<>(ibashiModel.getTimetable().getArrivalDepartures()));

        timeTableRV.setAdapter(adapterTimeTable);
    }

    @Override
    public void showLastUpdateTime() {
        Date date = new Date();
        String stringDate = DateFormat.getTimeInstance(DateFormat.SHORT).format(date);
        Snackbar.make(logoIV,
                getString(R.string.last_update, stringDate), Snackbar.LENGTH_LONG).show();
    }

    // The Adapter lives within the view since is the only class who access it
    class TimetableAdapter extends RecyclerView.Adapter<TimetableAdapter.TimetableViewHolder> {

        ArrayList<ArrivalDeparture> departures;

        TimetableAdapter(ArrayList<ArrivalDeparture> departures) {
            this.departures = departures;
        }

        @Override
        public TimetableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View root = LayoutInflater.from(getActivity().getApplicationContext()).
                    inflate(R.layout.item_timetable_departure, parent, false);
            return new TimetableViewHolder(root);
        }

        @Override
        public void onBindViewHolder(TimetableViewHolder holder, int position) {
            ArrivalDeparture departure = departures.get(position);
            holder.setDeparture(departure);
        }

        @Override
        public int getItemCount() {
            return departures.size();
        }

        class TimetableViewHolder extends RecyclerView.ViewHolder {

            TextView linecode;
            TextView direction;
            TextView datetime;

            TimetableViewHolder(View itemView) {
                super(itemView);
                this.linecode = itemView.findViewById(R.id.linecodeTV);
                this.direction = itemView.findViewById(R.id.directionTV);
                this.datetime = itemView.findViewById(R.id.daytimeTV);
            }

            void setDeparture(ArrivalDeparture departure) {
                this.linecode.setText(departure.getLineCode());
                this.direction.setText(departure.getDirection());
                this.datetime.setText(departure.getDatetime().getTimestamp());
            }
        }
    }
}
