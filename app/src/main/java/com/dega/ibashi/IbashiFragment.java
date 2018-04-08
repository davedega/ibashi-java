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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dega.ibashi.model.Departure;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class IbashiFragment extends Fragment implements IbashiContract.View {

    private IbashiContract.Presenter presenter;
    private RecyclerView timeTableRV;
    private LinearLayout loadingContainer;
    private ProgressBar progressBar;
    private TextView updateTextView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ibashi, container, false);
        timeTableRV = rootView.findViewById(R.id.timeTableRV);
        loadingContainer = rootView.findViewById(R.id.loadingContainer);
        progressBar = rootView.findViewById(R.id.progressBar);
        updateTextView = rootView.findViewById(R.id.updateTextView);
        return rootView;
    }

    @Override
    public void setPresenter(IbashiContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showErrorMessage(int string) {
        updateTextView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        timeTableRV.setVisibility(View.GONE);
        Snackbar mySnackbar = Snackbar.make(loadingContainer,
                getString(string), Snackbar.LENGTH_SHORT);
        mySnackbar.setAction(R.string.try_again, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                updateTextView.setVisibility(View.VISIBLE);
                presenter.loadTimeTable();
            }
        });

        mySnackbar.show();
    }

    @Override
    public void showDepartures(List<Departure> departures) {
        timeTableRV.setVisibility(View.VISIBLE);
        loadingContainer.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        updateTextView.setVisibility(View.GONE);

        LinearLayoutManager mLayoutManager = new
                LinearLayoutManager(getActivity());
        timeTableRV.setLayoutManager(mLayoutManager);

        TimetableAdapter adapterTimeTable = new
                TimetableAdapter(departures);

        timeTableRV.setAdapter(adapterTimeTable);
    }

    @Override
    public void showLastUpdateTime() {
        Date date = new Date();
        String stringDate = DateFormat.getTimeInstance(DateFormat.SHORT).format(date);
        Snackbar.make(loadingContainer,
                getString(R.string.last_update, stringDate), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showEmptyList() {
        //todo work together with UX/UI team to design and implement empty screen
    }

    // The Adapter lives within the view since is the only class who access it
    class TimetableAdapter extends RecyclerView.Adapter<TimetableAdapter.TimetableViewHolder> {

        final ArrayList<Departure> departures;

        TimetableAdapter(List<Departure> departures) {
            this.departures = new ArrayList<>(departures);
        }

        @Override
        public TimetableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View root = LayoutInflater.from(getActivity()).
                    inflate(R.layout.item_timetable_departure, parent, false);
            return new TimetableViewHolder(root);
        }

        @Override
        public void onBindViewHolder(TimetableViewHolder holder, int position) {
            holder.setDeparture(departures.get(position));
        }

        @Override
        public int getItemCount() {
            return departures.size();
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        class TimetableViewHolder extends RecyclerView.ViewHolder {

            final TextView linecode;
            final TextView direction;
            final TextView datetime;

            TimetableViewHolder(View itemView) {
                super(itemView);
                this.direction = itemView.findViewById(R.id.directionTV);
                this.datetime = itemView.findViewById(R.id.daytimeTV);
                this.linecode = itemView.findViewById(R.id.linecodeTV);
            }

            void setDeparture(Departure departure) {
                this.linecode.setText(departure.getLineCode());
                this.direction.setText(departure.getDirection());
                this.datetime.setText(TimeUtil.formatTime(departure.getDatetime()));
            }
        }
    }
}
