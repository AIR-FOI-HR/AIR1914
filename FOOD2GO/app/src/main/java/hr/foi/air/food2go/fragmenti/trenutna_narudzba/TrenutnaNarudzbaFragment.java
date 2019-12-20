package hr.foi.air.food2go.fragmenti.trenutna_narudzba;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.core.Artikl;
import hr.foi.air.core.BodoviVjernostiView;
import hr.foi.air.core.Korisnik;
import hr.foi.air.core.Racun;
import hr.foi.air.food2go.controller.Internet;
import hr.foi.air.food2go.controller.dataLoaders.DataLoadedListener;
import hr.foi.air.food2go.controller.dataLoaders.WsDataLoader;
import hr.foi.air.food2go.fragmenti.odabir_potkategorije.OdabirPotkategorijeFragment;
import hr.foi.air.food2go.recyclerview.TrenutnaNarudzbaRecyclerAdapter;

import hr.foi.air.food2go.R;

public class TrenutnaNarudzbaFragment extends Fragment implements DataLoadedListener, View.OnClickListener {


    private ArrayList<Artikl> artikliNarudzbe = new ArrayList<>();
    private WsDataLoader wsDataLoader;
    private float ukupnaCijena;
    private BodoviVjernostiView bodoviVjernostiView = null;
    public static boolean iskoristenPopust = false;
    private Type entityType;
    private boolean dodaneStavke = false;
    private Racun racun = null;
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

    public void IzracunajUkupno() {
        Float ukupno2 = 0.0f;
        for (Artikl a : artikliNarudzbe) {
            Float cijenaArtikla = a.getCijena();
            int kolicina = a.getKolicina();
            ukupno2 += (cijenaArtikla * kolicina);
        }

        ukupno.setText(ukupno2.toString());
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        try {
            getSharedPrefs();
            artikliNarudzbe = OdabirPotkategorijeFragment.listaArtikalaUKosarici;
            IzracunajUkupno();
            wsDataLoader = new WsDataLoader();
            iskoristenPopust=false;
            dodaneStavke = false;
            DohvatiIzgled();
            wsDataLoader.DohvatiArtiklePoKategoriji(this, "1");
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    @Override
    public void onDataLoaded(String message, String status, Object data) {

        if (entityType == BodoviVjernostiView.class  ) {
            if(status.equals("OK")){
                uracunajPopust(status, (BodoviVjernostiView) data);
            }else {
                Toast.makeText(getContext(),"Nemate pravo na popust !",Toast.LENGTH_SHORT).show();
            }

        }

        radSNarudzbom(data);
    }

    private void radSNarudzbom(Object data) {

        if (entityType == Racun.class && dodaneStavke == false) {
            dodajArtikleNaRacun((Racun) data);


        }
    }

    private void getSharedPrefs() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
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
        entityType = BodoviVjernostiView.class;
        wsDataLoader.IskoristiBodoveVjernosti(Korisnik.getPrijavljeniKorisnik());

    }

    private void uracunajPopust(String status, BodoviVjernostiView data) {
        if (status.equals("OK")) {
            bodoviVjernostiView = data;
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

    @OnClick(R.id.uiActionNaruci)
    void kreirajNarudzbu() {
        entityType = Racun.class;
        if (Internet.isNetworkAvailable(getContext()) == true) {
            if(artikliNarudzbe.size()==0){
                Toast.makeText(getContext(),"Košarica je prazna !!!",Toast.LENGTH_SHORT).show();
            }else{
                if (iskoristenPopust == true) {
                    wsDataLoader.ZabiljeziBodoveVjernosti(Korisnik.getPrijavljeniKorisnik(), bodoviVjernostiView);
                }
                wsDataLoader.KreirajRacun(Korisnik.getPrijavljeniKorisnik());
                if (racun != null) {
                    dodajArtikleNaRacun(racun);
                }
            }
            }
             else {
            Toast.makeText(getContext(), "Nema interneta", Toast.LENGTH_SHORT).show();
        }

    }

    private void dodajArtikleNaRacun(Racun racun) {

        float cijena = Float.parseFloat(ukupno.getText().toString());
        wsDataLoader.DodajCijenuNaRacun(racun, cijena);
        for (Artikl artikl : OdabirPotkategorijeFragment.listaArtikalaUKosarici) {
            if (artikl.getKolicina() > 0) {
                wsDataLoader.dodajArtikleNaNarudzbe(artikl, racun);
            }
        }
        dodaneStavke = true;
        AlertDialog alert = new AlertDialog.Builder(getContext()).create();
        alert.setTitle("Uspjeh !");
        alert.setMessage("Artikli su dodani na racun !");
        alert.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
        artikliNarudzbe.clear();
        OdabirPotkategorijeFragment.listaArtikalaUKosarici.clear();
        DohvatiIzgled();
        IzracunajUkupno();
    }


    @Override
    public void onClick(View v) {

    }
}