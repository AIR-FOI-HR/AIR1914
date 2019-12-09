package hr.foi.air.food2go;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import hr.foi.air.food2go.model.Artikl;
import hr.foi.air.food2go.recyclerview.OdabirKategorijeRecyclerAdapter;

public class OdabirKategorijeActivity extends AppCompatActivity {

    private ArrayList<Artikl> artikli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odabir_kategorije);

        artikli = new ArrayList<>();
        mockData();
        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.artikli_recyclerview);
        OdabirKategorijeRecyclerAdapter adapter = new OdabirKategorijeRecyclerAdapter(this, artikli);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void mockData(){
        Artikl a = new Artikl(1, "Hamburger", "https://i.postimg.cc/V6LwCJDf/hamburger.jpg", 18.446f, 10, 5, "Hrana");
        Artikl b = new Artikl(2, "Fanta", "https://i.postimg.cc/bJ79Dt9W/fanta.jpg", 13.444f, 10, 5, "Piće");
        artikli.add(a);
        artikli.add(b);
        for (int i=0; i<7; i++){
            artikli.add(a);
        }
    }
}
