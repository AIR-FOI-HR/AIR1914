package hr.foi.air.food2go;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.food2go.controller.Internet;
import hr.foi.air.food2go.controller.RegistracijaActivity;

public class GlavniZaslonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glavni_zaslon);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.hranaKategorija)
    public void KlikHrana(View v){
        if(Internet.isNetworkAvailable(this) == true) {
            Toast.makeText(getApplicationContext(), "Odabrana kategorija: Hrana", Toast.LENGTH_SHORT).show();
        }
        else {
            AlertDialog alertDialog = new AlertDialog.Builder(GlavniZaslonActivity.this).create();
            alertDialog.setTitle("Pogreška u internet vezi");
            alertDialog.setMessage("Molimo Vas omogućite internetsku vezu kako bi ste se prijavili u aplikaciju.");
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

    @OnClick(R.id.piceKategorija)
    public void KlikPice(View v){
        if(Internet.isNetworkAvailable(this) == true) {
            Toast.makeText(getApplicationContext(), "Odabrana kategorija: Piće", Toast.LENGTH_SHORT).show();
        }
        else {
            AlertDialog alertDialog = new AlertDialog.Builder(GlavniZaslonActivity.this).create();
            alertDialog.setTitle("Pogreška u internet vezi");
            alertDialog.setMessage("Molimo Vas omogućite internetsku vezu kako bi ste se prijavili u aplikaciju.");
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
