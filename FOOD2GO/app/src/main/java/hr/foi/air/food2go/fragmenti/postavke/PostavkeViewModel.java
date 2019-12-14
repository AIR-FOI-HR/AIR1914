package hr.foi.air.food2go.fragmenti.postavke;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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

public class PostavkeViewModel extends Fragment {
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_postavke, container, false);
        unbinder= ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        bindData();

    }

    private void bindData() {
        uiKorisnikIme.setText(Korisnik.getPrijavljeniKorisnik().getIme());
        uiKorisnikPrezime.setText(Korisnik.getPrijavljeniKorisnik().getPrezime());
        uiKorisnikEmail.setText(Korisnik.getPrijavljeniKorisnik().getEmail());
        uiKorisnikUsername.setText(Korisnik.getPrijavljeniKorisnik().getUsername());
        uiKorisnikLozinka.setText(Korisnik.getPrijavljeniKorisnik().getLozinka());
        uiKorisnikPonoviLozinku.setText(Korisnik.getPrijavljeniKorisnik().getLozinka());
        uiKorisnikAdresa.setText(Korisnik.getPrijavljeniKorisnik().getAdresa());
        uiKorisnikMobitel.setText(Korisnik.getPrijavljeniKorisnik().getMobitel());
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.uredi_korisnicke_postavke)
    void AzurirajKorisnika(){

    }

}