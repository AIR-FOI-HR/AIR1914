package hr.foi.air.food2go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import hr.foi.air.core.Korisnik;
import hr.foi.air.food2go.controller.dataLoaders.DataLoadedListener;
import hr.foi.air.food2go.controller.dataLoaders.WsDataLoader;

public class StanjeBodovaActivity extends AppCompatActivity implements DataLoadedListener {

    private Korisnik korisnik;
    private String username;
    private TextView brojBodova;
    private WsDataLoader wsDataLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stanje_bodova);
        brojBodova = findViewById(R.id.broj_bodova);

        getSharedPref();
        if(username != "userNotFound"){
            korisnik = new Korisnik();
            korisnik.setUsername(username);
            wsDataLoader = new WsDataLoader();
            wsDataLoader.DohvatiTrenutneBodove(korisnik, this);
        }
    }

    private void getSharedPref(){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        username = pref.getString("username", "userNotFound");
    }


    @Override
    public void onDataLoaded(String message, String status, Object data) {
        if(status == "OK"){
            brojBodova.setText(Integer.toString((int)data));
        }
        else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT);
        }
    }
}
