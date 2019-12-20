package hr.foi.air.food2go.fragmenti.postavke;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hr.foi.air.core.Korisnik;
import hr.foi.air.food2go.R;
import hr.foi.air.food2go.controller.Internet;
import hr.foi.air.food2go.controller.dataLoaders.DataLoadedListener;
import hr.foi.air.food2go.controller.dataLoaders.WsDataLoader;

public class PostavkeFragment extends Fragment implements DataLoadedListener {
    @BindView(R.id.prijavljeni_korisnik_ime)
    public EditText uiKorisnikIme;
    @BindView(R.id.prijavljeni_korisnik_prezime)
    public EditText uiKorisnikPrezime;
    @BindView(R.id.prijavljeni_korisnik_adresa)
    public EditText uiKorisnikAdresa;
    @BindView(R.id.prijavljeni_korisnik_username)
    public EditText uiKorisnikUsername;
    @BindView(R.id.prijavljeni_korisnik_email)
    public EditText uiKorisnikEmail;
    @BindView(R.id.prijavljeni_korisnik_lozinka)
    public EditText uiKorisnikLozinka;
    @BindView(R.id.prijavljeni_korisnik_ponovi_lozinku)
    public EditText uiKorisnikPonoviLozinku;
    @BindView(R.id.prijavljeni_korisnik_mobitel)
    public EditText uiKorisnikMobitel;

    @BindView(R.id.uredi_korisnicke_postavke)
    public Button urediKorisnika;

    private Unbinder unbinder;

    private WsDataLoader dataLoader;
    private Korisnik noviKorisnik = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_postavke, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        bindData();

    }

    public void bindData() {
        uiKorisnikIme.setText(Korisnik.getPrijavljeniKorisnik().getIme());
        uiKorisnikPrezime.setText(Korisnik.getPrijavljeniKorisnik().getPrezime());
        uiKorisnikEmail.setText(Korisnik.getPrijavljeniKorisnik().getEmail());
        uiKorisnikUsername.setText(Korisnik.getPrijavljeniKorisnik().getUsername());
        uiKorisnikLozinka.setText(Korisnik.getPrijavljeniKorisnik().getLozinka());
        uiKorisnikPonoviLozinku.setText(Korisnik.getPrijavljeniKorisnik().getLozinka());
        uiKorisnikAdresa.setText(Korisnik.getPrijavljeniKorisnik().getAdresa());
        uiKorisnikMobitel.setText(Korisnik.getPrijavljeniKorisnik().getMobitel());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private boolean ispravnostPodataka() {
        return (PostavkeValidacije.PraznoPolje(uiKorisnikIme.getText().toString().trim()) == true && PostavkeValidacije.PraznoPolje(uiKorisnikPrezime.getText().toString().trim()) == true
                && PostavkeValidacije.ProvijeriEmail(uiKorisnikEmail.getText().toString().trim()) == true && PostavkeValidacije.PraznoPolje(uiKorisnikUsername.getText().toString().trim()) == true
                && PostavkeValidacije.PraznoPolje(uiKorisnikLozinka.getText().toString().trim()) == true && PostavkeValidacije.PraznoPolje(uiKorisnikPonoviLozinku.getText().toString().trim()) == true
                && PostavkeValidacije.IspravnostLozinki(uiKorisnikLozinka.getText().toString().trim(), uiKorisnikPonoviLozinku.getText().toString().trim()) == true) ? true : false;
    }

    private void instanceNewUserData() {
        noviKorisnik = new Korisnik();
        noviKorisnik.setId(Korisnik.getPrijavljeniKorisnik().getId());
        noviKorisnik.setIme(uiKorisnikIme.getText().toString().trim());
        noviKorisnik.setPrezime(uiKorisnikPrezime.getText().toString().trim());
        noviKorisnik.setUsername(uiKorisnikUsername.getText().toString());
        noviKorisnik.setEmail(uiKorisnikEmail.getText().toString());
        noviKorisnik.setAdresa(uiKorisnikAdresa.getText().toString());
        noviKorisnik.setMobitel(uiKorisnikMobitel.getText().toString());
        noviKorisnik.setLozinka(uiKorisnikLozinka.getText().toString());
        noviKorisnik.setBrojBodova(Korisnik.getPrijavljeniKorisnik().getBrojBodova());
        noviKorisnik.setBrojPokusaja(0);
        noviKorisnik.setOib(Korisnik.getPrijavljeniKorisnik().getOib());
        noviKorisnik.setStatus(Korisnik.getPrijavljeniKorisnik().getStatus());

    }

    @OnClick(R.id.uredi_korisnicke_postavke)
    void AzurirajPodatke() {
        if (!ispravnostPodataka()) {
            Toast.makeText(getContext(), "Nije ok", Toast.LENGTH_SHORT).show();

        } else {
            if (Internet.isNetworkAvailable(getContext()) == true) {
                instanceNewUserData();
                dataLoader = new WsDataLoader();
                dataLoader.AzurirajKorisnika(noviKorisnik, this);

            } else {
                Toast.makeText(getContext(), "Nema interneta", Toast.LENGTH_LONG).show();
            }


        }

    }


    @Override
    public void onDataLoaded(String message, String status, Object data) {
        if(status.equals("OK")){
            Toast.makeText(getContext(), "Podaci su uspješno ažurirani", Toast.LENGTH_LONG).show();
            Korisnik.setPrijavljeniKorisnik(noviKorisnik);
        }else{
            Toast.makeText(getContext(), "Podaci nisu ažurirani", Toast.LENGTH_LONG).show();
        }
    }
}