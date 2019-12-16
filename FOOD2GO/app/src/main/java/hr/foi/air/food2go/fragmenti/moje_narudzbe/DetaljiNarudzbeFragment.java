package hr.foi.air.food2go.fragmenti.moje_narudzbe;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.core.StavkeRacuna;
import hr.foi.air.food2go.R;
import hr.foi.air.food2go.controller.dataLoaders.DataLoadedListener;
import hr.foi.air.food2go.controller.dataLoaders.WsDataLoader;
import hr.foi.air.food2go.recyclerview.DetaljiNarudzbeAdapter;

public class DetaljiNarudzbeFragment extends Fragment implements DataLoadedListener {

    View v;
    private WsDataLoader wsDataLoader;
    public ArrayList<StavkeRacuna> artikli = new ArrayList<StavkeRacuna>();
    private String korisnickoIme;
    private String racunid;
    private String ukupnoCijena;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = container;
        return inflater.inflate(R.layout.fragment_detalji_narudzbe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        wsDataLoader = new WsDataLoader();
        wsDataLoader.IspisiArtikleRacuna(getRacunID(), this);
    }

    private String getRacunID(){
        racunid = MojeNarudzbeFragment.odabraniRacun.getID();
        return racunid;
    }

    private void DohvatiIzgled() {
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.detaljinarudzbe_recyclerview);
        DetaljiNarudzbeAdapter adapter = new DetaljiNarudzbeAdapter(getActivity(), artikli);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public String izracunajUkupnuCijenu(){
        int ukupno = 0;
        for (StavkeRacuna stavka: artikli) {
            ukupno += Integer.parseInt(stavka.getKolicina()) * Integer.parseInt(stavka.getArtiikl_Temporalno_Cijena());
        }
        ukupnoCijena = String.valueOf(ukupno);

        return ukupnoCijena;
    }

    public void setText(String text) {
        TextView t = (TextView) v.findViewById(R.id.uiukupno);  //UPDATE
        t.setText(text);
    }

    @Override
    public void onDataLoaded(String message, String status, Object data) {
        if(status.equals("OK")){
            List<StavkeRacuna> art = (List<StavkeRacuna>) data;
            for (StavkeRacuna a : art) {
                artikli.add(a);
                Log.i("tag", "artikli: " + a.getNaziv());
            }
            DohvatiIzgled();
            setText(izracunajUkupnuCijenu() + ",00 kn");
        }
        else{
            Toast.makeText(getActivity(), "Postoji problem.", Toast.LENGTH_SHORT).show();
        }
    }
}
