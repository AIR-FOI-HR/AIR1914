package hr.foi.air.food2go.fragmenti.nagrade;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.food2go.R;
import hr.foi.air.food2go.controller.dataLoaders.DataLoadedListener;
import hr.foi.air.food2go.controller.dataLoaders.WsDataLoader;
import hr.foi.air.core.Nagrada;
import hr.foi.air.food2go.recyclerview.NagradeRecyclerAdapter;

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

        wsDataLoader.DohvatiSveNagrade(this);
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = view.findViewById(R.id.nagrade_recyclerview);
        NagradeRecyclerAdapter adapter = new NagradeRecyclerAdapter(getActivity(), nagrade);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onDataLoaded(String message, String status, Object data) {
        if(status.equals("OK")){
            List<Nagrada> nag = (List<Nagrada>) data;
            for (Nagrada n : nag) {
                nagrade.add(n);
            }
            initRecyclerView();
        }
        else{
            Toast.makeText(getActivity(), "Postoji problem.", Toast.LENGTH_SHORT).show();
        }
    }
}