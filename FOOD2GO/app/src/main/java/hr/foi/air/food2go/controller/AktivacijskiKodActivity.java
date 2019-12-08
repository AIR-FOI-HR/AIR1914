package hr.foi.air.food2go.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.core.Korisnik;
import hr.foi.air.food2go.R;
import hr.foi.air.food2go.dataLoaders.DataLoadedListener;
import hr.foi.air.food2go.dataLoaders.WsDataLoader;

public class AktivacijskiKodActivity extends AppCompatActivity implements DataLoadedListener {

    private EditText email, aktivacijski;

    private WsDataLoader wsDataLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktivacijski_kod);
        ButterKnife.bind(this);

        email = findViewById(R.id.uiEmail);
        aktivacijski = findViewById(R.id.uiInputAktivacijskiKod);
    }

    @OnClick(R.id.uiActionAktiviraj)
    public void AktivacijaKlik(View v){
        String mEmail = email.getText().toString().trim();
        String mAktivacijskiKod = aktivacijski.getText().toString().trim();;

        if(mEmail.isEmpty()|| mAktivacijskiKod.isEmpty()){
            AlertDialog alertDialog = new AlertDialog.Builder(AktivacijskiKodActivity.this).create();
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
            Korisnik korisnik = new Korisnik(mEmail, mAktivacijskiKod);
            wsDataLoader = new WsDataLoader();
            wsDataLoader.Registracija(korisnik, this);
        }
    }

    @Override
    public void onDataLoaded(String message, String status, Object data) {
        if (status.equals("OK")){
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Uspješna aktivacija!");
            alertDialog.setMessage("Uspješno ste aktivirali svoj korisnički račun");
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //startActivity(new Intent(AktivacijskiKodActivity.this, LoginActivity.class)); // prebaciti na zaslon za prijavu
                        }
                    });
            alertDialog.show();
        }else{
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Neispravan aktivacijski kod!");
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
}
