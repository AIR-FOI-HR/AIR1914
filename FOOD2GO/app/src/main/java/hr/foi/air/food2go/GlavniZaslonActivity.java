package hr.foi.air.food2go;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class GlavniZaslonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glavni_zaslon);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.hranaKategorija)
    public void KlikHrana(View v){
        Toast.makeText(getApplicationContext(), "Odabrana kategorija: Hrana", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.piceKategorija)
    public void KlikPice(View v){
        Toast.makeText(getApplicationContext(), "Odabrana kategorija: Hrana", Toast.LENGTH_SHORT).show();
    }
}
