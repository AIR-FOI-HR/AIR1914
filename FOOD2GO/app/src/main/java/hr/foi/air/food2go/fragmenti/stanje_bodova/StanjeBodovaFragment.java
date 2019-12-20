package hr.foi.air.food2go.fragmenti.stanje_bodova;

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

import java.util.List;

import hr.foi.air.core.Korisnik;
import hr.foi.air.food2go.R;
import hr.foi.air.food2go.controller.dataLoaders.DataLoadedListener;
import hr.foi.air.food2go.controller.dataLoaders.WsDataLoader;

public class StanjeBodovaFragment extends Fragment implements DataLoadedListener {
    private Korisnik korisnik;
    private String username;
    private TextView brojBodova;
    private WsDataLoader wsDataLoader;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stanje_bodova, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        brojBodova = view.findViewById(R.id.broj_bodova);


        wsDataLoader = new WsDataLoader();
        getSharedPref();
        if(username != "userNotFound"){
            korisnik = new Korisnik();
            korisnik.setUsername(username);
            wsDataLoader.DohvatiTrenutneBodove(korisnik, this);
        }
    }


    private void getSharedPref(){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        username = pref.getString("korisnickoIme", "userNotFound");
    }

    @Override
    public void onDataLoaded(String message, String status, Object data) {
        if(status.equals("OK")){
            List<Korisnik> kor = (List<Korisnik>) data;
            for(Korisnik k : kor){
                korisnik = k;
            }
            brojBodova.setText(Integer.toString(korisnik.getBrojBodova()));
        }
        else {
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
    }
}