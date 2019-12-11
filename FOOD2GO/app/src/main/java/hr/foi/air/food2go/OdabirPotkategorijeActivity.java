package hr.foi.air.food2go;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.core.Artikl;

public class OdabirPotkategorijeActivity extends AppCompatActivity {

    public static ArrayList<Artikl> listaArtikalaUKosarici = new ArrayList<>();
    private Artikl odabraniArtikl;

    private TextView artikl;
    private ImageView artiklSlika;
    private TextView artiklOpis;
    private TextView artiklCijena;

    private Button gumbPlus;
    private TextView artiklKolicina;
    private Button gumbMinus;

    private Button dodajUKosaricu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odabir_potkategorije);
        ButterKnife.bind(this);

        odabraniArtikl = OdabirKategorijeActivity.Artikl;

        artikl = findViewById(R.id.artikl);
        artiklSlika = findViewById(R.id.artikl_slika);
        artiklOpis = findViewById(R.id.artikl_opis);
        artiklCijena = findViewById(R.id.artikl_cijena2);
        gumbPlus = findViewById(R.id.gumb_plus);
        artiklKolicina = findViewById(R.id.artikl_kolicina);
        gumbMinus = findViewById(R.id.gumb_minus);
        dodajUKosaricu = findViewById(R.id.gumb_dodaj_u_kosaricu);

        ucitajPodatke();
    }

    private void ucitajPodatke(){
        artikl.setText(odabraniArtikl.getNaziv());
        //slika
        Glide.with(this)
                .asBitmap()
                .load(odabraniArtikl.getUrlSlike())
                .into(artiklSlika);

        artiklOpis.setText("Opis: " + odabraniArtikl.getOpis());
        DecimalFormat df = new DecimalFormat("0.00");
        artiklCijena.setText("Cijena: " + df.format(odabraniArtikl.getCijena()).replace('.', ',') + " kn");
        artiklKolicina.setText("1");
    }

    @OnClick(R.id.gumb_plus)
    public void KlikGumbPlus(View v){

    }

    @OnClick(R.id.gumb_minus)
    public void KlikGumbMinus(View v){

    }

    @OnClick(R.id.gumb_dodaj_u_kosaricu)
    public void KlikDodajUKosaricu(View v){

    }
}
