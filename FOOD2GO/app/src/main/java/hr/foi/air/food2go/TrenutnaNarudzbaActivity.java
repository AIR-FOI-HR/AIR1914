package hr.foi.air.food2go;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.core.Artikl;
import hr.foi.air.food2go.controller.dataLoaders.DataLoadedListener;
import hr.foi.air.food2go.controller.dataLoaders.WsDataLoader;
import hr.foi.air.food2go.recyclerview.TrenutnaNarudzbaRecyclerAdapter;

public class TrenutnaNarudzbaActivity extends AppCompatActivity implements DataLoadedListener {

    private ArrayList<Artikl> artikliNarudzbe= new ArrayList<Artikl>();
    private WsDataLoader wsDataLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.fragment_trenutna_narudzba);
        DohvatiArtikleZaTest();
        Toast.makeText(this, "Postoji problem.", Toast.LENGTH_SHORT).show();
        wsDataLoader= new WsDataLoader();
        //saljes id racuna
        //Integer id_racun=2;
       // wsDataLoader.DohvatiArtikleTrenutneNarudzbe(this,id_racun);
    }

    private void DohvatiArtikleZaTest(){
        Artikl a = new Artikl(1,"Slika1","https://s2.vivre.eu/upload/2016/03/thumbs/56f55a858e68f2.56365829.800x675.jpg",
                10,10,5,"Hoho");
        Artikl b = new Artikl(1,"Slika2","https://s2.vivre.eu/upload/2016/03/thumbs/56f55a858e68f2.56365829.800x675.jpg",
                10,10,5,"Hoho");
        Artikl c = new Artikl(1,"Slika3","https://s2.vivre.eu/upload/2016/03/thumbs/56f55a858e68f2.56365829.800x675.jpg",
                10,10,5,"Hoho");
        artikliNarudzbe.add(a);
        artikliNarudzbe.add(b);
        artikliNarudzbe.add(c);
        initRecyclerView();
        Toast.makeText(this, "Doslo je do artikla.", Toast.LENGTH_SHORT).show();

    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.trenutna_narudzbaRecycler);
        TrenutnaNarudzbaRecyclerAdapter adapter = new TrenutnaNarudzbaRecyclerAdapter(this,artikliNarudzbe);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Toast.makeText(this, "Init rec.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDataLoaded(String message, String status, Object data) {
        if(status.equals("OK")){
            List<Artikl> art = (List<Artikl>)data;
            for(Artikl a :art){
                artikliNarudzbe.add(a);
            }
            initRecyclerView();
        }
    }
    @OnClick(R.id.uiActionIskoristi)
    public void Klik(View v){
        Toast.makeText(this, "Postoji problem.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
