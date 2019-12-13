package hr.foi.air.food2go.fragmenti.moje_narudzbe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

import hr.foi.air.core.Racun;
import hr.foi.air.food2go.R;
import hr.foi.air.food2go.controller.dataLoaders.DataLoadedListener;
import hr.foi.air.food2go.controller.dataLoaders.WsDataLoader;
import hr.foi.air.food2go.recyclerview.MojeNarudzbeAdapter;

public class MojeNarudzbeFragment extends Fragment implements View.OnClickListener, DataLoadedListener {

    private WsDataLoader wsDataLoader;
    private ArrayList<Racun> racuni;
    private String korisnickoIme;
    public static Racun Racun;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_moje_narudzbe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getSharedPrefs();
        RecyclerView narudzba = (RecyclerView) view.findViewById(R.id.narudzbe_recyclerview);

        narudzba.setOnClickListener(this);

        wsDataLoader = new WsDataLoader();
        racuni = new ArrayList<>();
        wsDataLoader.IspisiRacune(getKorisnickoIme(), this);

        MojeNarudzbeAdapter adapter = new MojeNarudzbeAdapter(getActivity(), racuni);
        narudzba.setAdapter(adapter);
        narudzba.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.narudzba:
                Toast.makeText(getActivity(), "Odabrali ste račun: " + Racun.getBrojRacuna(), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onDataLoaded(String message, String status, Object data) {
        if(status.equals("OK")){
            List<Racun> rac = (List<Racun>) data;
            for (Racun r : rac) {
                racuni.add(r);
            }
        }
        else{
            Toast.makeText(getActivity(), "Postoji problem.", Toast.LENGTH_SHORT).show();
        }
    }

    public static void PrikaziRacun(Context context){
        //treba prikazati detalje racuna
        //context.startActivity(new Intent(context, DetaljiNarudzbeActivity.class));
    }

    private void getSharedPrefs(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        setKorisnickoIme(prefs.getString("korisnickoIme","test@test.test"));
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }
}