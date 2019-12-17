package hr.foi.air.food2go.fragmenti.trenutna_narudzba;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.core.Artikl;
import hr.foi.air.core.BodoviVjernostiView;
import hr.foi.air.core.Korisnik;
import hr.foi.air.food2go.controller.dataLoaders.DataLoadedListener;
import hr.foi.air.food2go.controller.dataLoaders.WsDataLoader;
import hr.foi.air.food2go.recyclerview.TrenutnaNarudzbaRecyclerAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import hr.foi.air.core.Artikl;
import hr.foi.air.food2go.R;

public class TrenutnaNarudzbaViewModel extends Fragment implements DataLoadedListener, View.OnClickListener {


    private ArrayList<Artikl> artikliNarudzbe = new ArrayList<Artikl>();
    private WsDataLoader wsDataLoader;
    private float ukupnaCijena;
    private boolean iskoristenPopust = false;
    View v;
    @BindView(R.id.txtCijena)
    TextView ukupno;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = container;
        View view = inflater.inflate(R.layout.fragment_trenutna_narudzba, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        getSharedPrefs();
        wsDataLoader = new WsDataLoader();
        wsDataLoader.DohvatiArtiklePoKategoriji(this, "1");
    }

    @Override
    public void onDataLoaded(String message, String status, Object data) {
        DohvatiArtikleZaTest();
        DohvatiIzgled();
        uracunajPopust(status, (BodoviVjernostiView) data);

    }

    private void getSharedPrefs() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    private void DohvatiArtikleZaTest() {
        Artikl a = new Artikl(1, "Slika1", "https://s2.vivre.eu/upload/2016/03/thumbs/56f55a858e68f2.56365829.800x675.jpg",
                10, 10, 5, "Hoho");
        Artikl b = new Artikl(1, "Slika2", "https://s2.vivre.eu/upload/2016/03/thumbs/56f55a858e68f2.56365829.800x675.jpg",
                10, 10, 5, "Hoho");
        Artikl c = new Artikl(1, "Slika3", "https://s2.vivre.eu/upload/2016/03/thumbs/56f55a858e68f2.56365829.800x675.jpg",
                10, 10, 5, "Hoho");
        Artikl d = new Artikl(1, "Slika4", "https://s2.vivre.eu/upload/2016/03/thumbs/56f55a858e68f2.56365829.800x675.jpg",
                10, 10, 5, "Hoho");
        Artikl f = new Artikl(1, "Slika5", "https://s2.vivre.eu/upload/2016/03/thumbs/56f55a858e68f2.56365829.800x675.jpg",
                10, 10, 5, "Hoho");
        artikliNarudzbe.add(a);
        artikliNarudzbe.add(b);
        artikliNarudzbe.add(c);
        artikliNarudzbe.add(d);
        artikliNarudzbe.add(f);

    }

    private void DohvatiIzgled() {
        RecyclerView recyclerView = v.findViewById(R.id.trenutna_narudzbaRecycler);
        TrenutnaNarudzbaRecyclerAdapter adapter = new TrenutnaNarudzbaRecyclerAdapter(getActivity(), artikliNarudzbe);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setOnClickListener(this);
    }

    @OnClick(R.id.uiActionIskoristi)
    public void IskoristiBodove() {
        wsDataLoader.IskoristiBodoveVjernosti(Korisnik.getPrijavljeniKorisnik());

    }

    private void uracunajPopust(String status, BodoviVjernostiView data) {
        if (status.equals("OK")) {
            AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
            alertDialog.setTitle("Iskoristi bodove");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Poštovani, imate ").append(data.getBrojBodova()).
                    append(" bodova i imate pravo na ").append(data.getPopust()).append("% popusta, želite li iskoristiti nagradu ?");
            alertDialog.setMessage(stringBuilder.toString());
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Iskoristi",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if (iskoristenPopust == false) {
                                float popust = Float.parseFloat(ukupno.getText().toString()) * data.getPopust() / 100;
                                ukupnaCijena = Float.parseFloat(ukupno.getText().toString()) - popust;
                                ukupno.setText(String.valueOf(ukupnaCijena));
                                iskoristenPopust = true;
                                dialog.cancel();
                            } else {
                                AlertDialog alert = new AlertDialog.Builder(getContext()).create();
                                alert.setTitle("Upozorenje !");
                                alert.setMessage("Već ste iskoristili nagradu za ovaj račun !!!");
                                alert.setButton(AlertDialog.BUTTON_POSITIVE, "U redu", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                alert.show();
                            }
                        }
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Odustani", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
        }
    }

    @Override
    public void onClick(View v) {

    }
}