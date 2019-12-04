package hr.foi.air.food2go.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    private Button btn_prijava;
    //private static String URL_LOGIN = "https://airfood2go.000webhostapp.com/Android/login.php";

    private WsDataLoader wsDataLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);

        email = findViewById(R.id.korisnickoIme);
        lozinka = findViewById(R.id.lozinka);
        btn_prijava = findViewById(R.id.uiActionPrijaviSe);
    }

    @OnClick(R.id.prijaviSe)
    public void KlikPrijava(View v) {
        String korisnickoIme = email.getText().toString().trim();
        String password = lozinka.getText().toString().trim();

        Korisnik korisnik = new Korisnik(korisnickoIme, password);
        wsDataLoader = new WsDataLoader();
        wsDataLoader.Prijava(korisnik,this);
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
