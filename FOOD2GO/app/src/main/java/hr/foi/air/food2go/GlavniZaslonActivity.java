package hr.foi.air.food2go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class GlavniZaslonActivity extends AppCompatActivity {

    public static String Kategorija;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glavni_zaslon);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.hranaKategorija)
    public void KlikHrana(View v){
        Kategorija = "1";
        startActivity(new Intent(this, OdabirKategorijeActivity.class));
    }

    @OnClick(R.id.piceKategorija)
    public void KlikPice(View v){
        Kategorija = "2";
        startActivity(new Intent(this, OdabirKategorijeActivity.class));
    }
}
