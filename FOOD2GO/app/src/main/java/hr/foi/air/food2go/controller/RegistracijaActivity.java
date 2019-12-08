package hr.foi.air.food2go.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.core.Korisnik;
import hr.foi.air.food2go.R;
import hr.foi.air.food2go.dataLoaders.DataLoadedListener;
import hr.foi.air.food2go.dataLoaders.WsDataLoader;

public class RegistracijaActivity extends AppCompatActivity implements DataLoadedListener {

    private EditText ime, prezime, korisnickoIme, lozinka, oib, email, adresa, brojMobitela;

    private WsDataLoader wsDataLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija);
        ButterKnife.bind(this);

        ime = findViewById(R.id.uiInputIme);
        prezime = findViewById(R.id.uiInputPrezime);
        korisnickoIme = findViewById(R.id.uiKorisnickoIme);
        lozinka = findViewById(R.id.uiInputLozinka);
        oib = findViewById(R.id.uiInputOib);
        email = findViewById(R.id.uiInputEmail);
        adresa = findViewById(R.id.uiInputAdresa);
        brojMobitela = findViewById(R.id.uiInputBrojMobitela);
    }

    @OnClick(R.id.registriraj)
    public void RegistracijaKlik(View v){
        String mIme = ime.getText().toString().trim();
        String mPrezime = prezime.getText().toString().trim();
        String mKorIme = korisnickoIme.getText().toString().trim();
        String mLozinka = lozinka.getText().toString().trim();
        String mOib = oib.getText().toString().trim();
        String mEmail = email.getText().toString().trim();
        String mAdresa = adresa.getText().toString().trim();
        String mBrojMobitela = brojMobitela.getText().toString().trim();
        String mAktivacijskiKod = generirajAktivacijskiKod(10);

        if(mIme.isEmpty() || mPrezime.isEmpty()|| mKorIme.isEmpty()|| mLozinka.isEmpty()||
                mOib.isEmpty()|| mEmail.isEmpty()|| mAdresa.isEmpty()|| mBrojMobitela.isEmpty()){

            AlertDialog alertDialog = new AlertDialog.Builder(RegistracijaActivity.this).create();
            alertDialog.setTitle("Nisu popunjeni svi podaci!");
            alertDialog.setMessage("Molimo Vas unesite sve podatke!");
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        else {
            Korisnik korisnik = new Korisnik(mIme, mPrezime, mKorIme,
                    mLozinka, mOib, mEmail, mAdresa,
                    mBrojMobitela, mAktivacijskiKod);
            wsDataLoader = new WsDataLoader();
            wsDataLoader.Registracija(korisnik, this);
        }
    }

    @Override
    public void onDataLoaded(String message, String status, Object data) {
        if (status.equals("OK")){
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Uspješna registracija!");
            alertDialog.setMessage("E-mail s aktivacijskim kodom Vam je poslan.");
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss(); // prebaciti na zaslon za aktivaciju
                        }
                    });
            alertDialog.show();
        }else{
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Korisnik postoji!");
            alertDialog.setMessage(message);
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

    public static final String DATA = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static Random RANDOM = new Random();

    public static String generirajAktivacijskiKod(int len) {
        StringBuilder sb = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            sb.append(DATA.charAt(RANDOM.nextInt(DATA.length())));
        }

        return sb.toString();
    }
}
