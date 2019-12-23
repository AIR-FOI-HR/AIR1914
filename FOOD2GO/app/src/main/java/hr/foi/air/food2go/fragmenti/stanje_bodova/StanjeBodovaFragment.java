package hr.foi.air.food2go.fragmenti.stanje_bodova;

import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.List;

import hr.foi.air.core.Korisnik;
import hr.foi.air.food2go.GlavniActivity;
import hr.foi.air.food2go.R;
import hr.foi.air.food2go.controller.Internet;
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

        if (Internet.isNetworkAvailable(getContext()) == true) {
            if(username != "userNotFound"){
                korisnik = new Korisnik();
                korisnik.setUsername(username);
                wsDataLoader.DohvatiTrenutneBodove(korisnik, this);
            }
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
            alertDialog.setTitle("Pogreška u internet vezi");
            alertDialog.setMessage("Molimo Vas omogućite internetsku vezu kako biste koristili aplikaciju.");
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
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