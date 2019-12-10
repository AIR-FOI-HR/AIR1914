package hr.foi.air.food2go;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        wsDataLoader = new WsDataLoader();
        artikli = new ArrayList<>();
        wsDataLoader.DohvatiArtiklePoKategoriji(this, "2");
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
}
