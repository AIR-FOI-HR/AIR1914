package hr.foi.air.food2go;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.ButterKnife;
import hr.foi.air.core.Korisnik;

public class StanjeBodovaActivity extends AppCompatActivity {

    private Korisnik korisnik;
    private TextView brojBodova;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stanje_bodova);
        brojBodova = findViewById(R.id.broj_bodova);

        korisnik = new Korisnik();
        postaviBrojBodova(42);
        prikaziBrojBodova();
    }

    //metoda za potrebe testiranja
    private void postaviBrojBodova(int brojBodova){
        korisnik.setBrojBodova(brojBodova);
    }

    private void prikaziBrojBodova(){
        brojBodova.setText(Integer.toString(korisnik.getBrojBodova()));
    }
}
