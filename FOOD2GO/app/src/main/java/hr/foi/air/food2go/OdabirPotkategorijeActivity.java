package hr.foi.air.food2go;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.core.Artikl;
import hr.foi.air.food2go.dataLoaders.WsDataLoader;

public class OdabirPotkategorijeActivity extends AppCompatActivity {

    public static ArrayList<Artikl> listaArtikalaUKosarici = new ArrayList<>();
    private Artikl odabraniArtikl;
    private int brojac;
    private WsDataLoader wsDataLoader;

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

        wsDataLoader = new WsDataLoader();

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
        brojac = 1;
        artiklKolicina.setText(Integer.toString(brojac));
    }

    @OnClick(R.id.gumb_plus)
    public void KlikGumbPlus(View v){
        if(brojac < 100){
            brojac++;
            artiklKolicina.setText(Integer.toString(brojac));
        }
    }

    @OnClick(R.id.gumb_minus)
    public void KlikGumbMinus(View v){
        if(brojac > 1){
            brojac--;
            artiklKolicina.setText(Integer.toString(brojac));
        }
    }

    @OnClick(R.id.gumb_dodaj_u_kosaricu)
    public void KlikDodajUKosaricu(View v){
        int kolicinaArtikla = brojac;
        boolean postoji = false;
        if(OdabirPotkategorijeActivity.listaArtikalaUKosarici.size()>0){
            for(Artikl a : OdabirPotkategorijeActivity.listaArtikalaUKosarici){
                if(a.getId()==odabraniArtikl.getId()){
                    kolicinaArtikla += a.getKolicina();
                    a.setKolicina(kolicinaArtikla);
                    postoji=true;
                }
            }
        }
        if(!postoji){
            odabraniArtikl.setKolicina(kolicinaArtikla);
            OdabirPotkategorijeActivity.listaArtikalaUKosarici.add(odabraniArtikl);
        }
        Toast.makeText(this, "Trenutna količina artikla u košarici: " + Integer.toString(kolicinaArtikla), Toast.LENGTH_SHORT).show();
    }
}
