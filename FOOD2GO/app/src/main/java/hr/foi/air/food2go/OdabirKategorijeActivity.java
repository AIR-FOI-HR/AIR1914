package hr.foi.air.food2go;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import hr.foi.air.core.Artikl;
import hr.foi.air.food2go.dataLoaders.DataLoadedListener;
import hr.foi.air.food2go.dataLoaders.WsDataLoader;
import hr.foi.air.food2go.recyclerview.OdabirKategorijeRecyclerAdapter;

public class OdabirKategorijeActivity extends AppCompatActivity implements DataLoadedListener {

    private WsDataLoader wsDataLoader;
    private ArrayList<Artikl> artikli;
    private TextView kategorija;
    public static Artikl Artikl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odabir_kategorije);

        kategorija = findViewById(R.id.kategorija);
        if(GlavniZaslonActivity.Kategorija == "1"){
            kategorija.setText("Hrana");
        }
        else {
            kategorija.setText("Piće");
        }
        wsDataLoader = new WsDataLoader();
        artikli = new ArrayList<>();
        wsDataLoader.DohvatiArtiklePoKategoriji(this, GlavniZaslonActivity.Kategorija);
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.artikli_recyclerview);
        OdabirKategorijeRecyclerAdapter adapter = new OdabirKategorijeRecyclerAdapter(this, artikli);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onDataLoaded(String message, String status, Object data) {
        if(status.equals("OK")){
            List<Artikl> art = (List<Artikl>) data;
            for (Artikl a : art) {
                artikli.add(a);
            }
            initRecyclerView();
        }
        else{
            Toast.makeText(this, "Postoji problem.", Toast.LENGTH_SHORT).show();
        }
    }

    public static void PrikaziArtikl(Context context){
        context.startActivity(new Intent(context, OdabirPotkategorijeActivity.class));
    }
}
