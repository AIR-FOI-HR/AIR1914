package hr.foi.air.food2go.fragmenti.odabir_kategorije;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.core.Artikl;
import hr.foi.air.food2go.GlavniZaslonActivity;
import hr.foi.air.food2go.R;
import hr.foi.air.food2go.controller.dataLoaders.DataLoadedListener;
import hr.foi.air.food2go.controller.dataLoaders.WsDataLoader;
import hr.foi.air.food2go.fragmenti.kategorije.KategorijeViewModel;
import hr.foi.air.food2go.recyclerview.OdabirKategorijeRecyclerAdapter;

public class OdabirKategorije extends Fragment implements DataLoadedListener, View.OnClickListener{

    private WsDataLoader wsDataLoader;
    private ArrayList<hr.foi.air.core.Artikl> artikli;
    private TextView kategorija;
    public static Artikl Artikl;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = container;
        return inflater.inflate(R.layout.fragment_odabir_kategorije, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        kategorija = view.findViewById(R.id.kategorija);
        if(KategorijeViewModel.Kategorija == "1"){
            kategorija.setText("Hrana");
        }
        else {
            kategorija.setText("Piće");
        }
        wsDataLoader = new WsDataLoader();
        artikli = new ArrayList<>();
        wsDataLoader.DohvatiArtiklePoKategoriji(this, KategorijeViewModel.Kategorija);
    }

    @Override
    public void onDataLoaded(String message, String status, Object data) {
        if(status.equals("OK")){
            List<Artikl> art = (List<Artikl>) data;
            for (Artikl a : art) {
                artikli.add(a);
            }
            initRecyclerView();
        }
        else{
            Toast.makeText(getActivity(), "Postoji problem.", Toast.LENGTH_SHORT).show();
        }
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = view.findViewById(R.id.artikli_recyclerview);
        OdabirKategorijeRecyclerAdapter adapter = new OdabirKategorijeRecyclerAdapter(getActivity(), artikli);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setOnClickListener(this);
    }

    public static void PrikaziArtikl(Context context){

    }

    @Override
    public void onClick(View v) {

    }
}
