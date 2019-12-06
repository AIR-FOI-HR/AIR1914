package hr.foi.air.food2go.controller;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
            Toast.makeText(getApplicationContext(),status,Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"Nije OK",Toast.LENGTH_SHORT).show();
        }
    }
}
