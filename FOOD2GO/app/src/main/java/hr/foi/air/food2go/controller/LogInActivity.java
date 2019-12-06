package hr.foi.air.food2go.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.core.Korisnik;
import hr.foi.air.food2go.MainActivity;
import hr.foi.air.food2go.R;
import hr.foi.air.food2go.controller.dataLoaders.DataLoadedListener;
import hr.foi.air.food2go.controller.dataLoaders.WsDataLoader;

public class LogInActivity extends AppCompatActivity implements DataLoadedListener {

    private EditText email, lozinka;

    private WsDataLoader wsDataLoader;

    private boolean prijavljen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);

        email = findViewById(R.id.korisnickoIme);
        lozinka = findViewById(R.id.lozinka);
    }

    @OnClick(R.id.prijava)
    public void KlikPrijava(View v) {
        if(Internet.isNetworkAvailable(this) == true) {
            String korisnickoIme = email.getText().toString().trim();
            String password = lozinka.getText().toString().trim();

            Korisnik korisnik = new Korisnik(korisnickoIme, password);
            wsDataLoader = new WsDataLoader();
            wsDataLoader.Prijava(korisnik, this);
        }
        else{
            AlertDialog alertDialog = new AlertDialog.Builder(LogInActivity.this).create();
            alertDialog.setTitle("Pogreška u internet vezi");
            alertDialog.setMessage("Molimo Vas, omogućite internetsku vezu kako bi ste se prijavili u aplikaciju.");
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

    @Override
    public void onDataLoaded(String message, String status, Object data) {
        if (status.equals("OK")){
            setSharedPrefs(email.getText().toString());
            if(checkLoginPersistence() == true){
                Intent i = new Intent(this, GlavniZaslon.class);
                startActivityForResult(i, 1);
            }
        }else{
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Pogrešni podaci za prijavu!");
            alertDialog.setMessage("Molimo Vas unesite ispravne podatke!");
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

    private void setSharedPrefs(String korisnickoIme){
        long ts=System.currentTimeMillis()/1000;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("korisnickoIme", korisnickoIme);
        editor.putBoolean("prijavljen", true);
        editor.putLong("timestamp",ts);
        editor.apply();
    }

    private void deleteSharedPrefs(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("prijavljen", false);
        editor.remove("emailKorisnika");
        editor.remove("interval");
        editor.remove("timestamp");
        editor.apply();
    }

    private Boolean checkLoginPersistence(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prijavljen = prefs.getBoolean("prijavljen",false);
        return prijavljen;
    }
}