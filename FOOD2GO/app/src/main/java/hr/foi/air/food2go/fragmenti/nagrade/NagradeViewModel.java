package hr.foi.air.food2go.fragmenti.nagrade;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import hr.foi.air.food2go.R;
import hr.foi.air.food2go.controller.dataLoaders.DataLoadedListener;
import hr.foi.air.food2go.controller.dataLoaders.WsDataLoader;
import hr.foi.air.food2go.model.Nagrada;

public class NagradeViewModel extends Fragment implements DataLoadedListener {

    View view;
    private WsDataLoader wsDataLoader;
    private ArrayList<Nagrada> nagrade;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = container;
        return inflater.inflate(R.layout.fragment_nagrade, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        nagrade = new ArrayList<>();
        wsDataLoader = new WsDataLoader();
    }

    @Override
    public void onDataLoaded(String message, String status, Object data) {

    }
}