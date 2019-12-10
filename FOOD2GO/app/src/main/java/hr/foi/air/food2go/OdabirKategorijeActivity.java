package hr.foi.air.food2go;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import hr.foi.air.core.Artikl;
import hr.foi.air.food2go.dataLoaders.DataLoadedListener;
import hr.foi.air.food2go.dataLoaders.WsDataLoader;
import hr.foi.air.food2go.recyclerview.OdabirKategorijeRecyclerAdapter;

public class OdabirKategorijeActivity extends AppCompatActivity implements DataLoadedListener {

    private WsDataLoader wsDataLoader;
    private ArrayList<Artikl> artikli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odabir_kategorije);

        artikli = new ArrayList<>();
        wsDataLoader = new WsDataLoader();
        wsDataLoader.DohvatiArtiklePoKategoriji(this, "1");
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
            artikli = (ArrayList<Artikl>) data;
            //Artikl a = new Artikl(1, "jeej", "https://i.postimg.cc/FsPRZGm0/coca-cola.jpg", 13, 6, 1, "nsi");
            //artikli.add(a);
            //Toast.makeText(getApplicationContext(), artikli.get(0).getNaziv(), Toast.LENGTH_SHORT).show();
            initRecyclerView();
        }
        else{
            Toast.makeText(getApplicationContext(), "Postoji problem.", Toast.LENGTH_SHORT).show();
        }
    }
}
