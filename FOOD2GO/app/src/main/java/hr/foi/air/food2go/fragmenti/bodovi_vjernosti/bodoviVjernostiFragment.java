package hr.foi.air.food2go.fragmenti.bodovi_vjernosti;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pinloyalitypointsupdate.codeLoyalityPointsFragment.LoyalityPontsWithCodeFragment;

import hr.foi.air.core.Korisnik;
import hr.foi.air.core.Racun;
import hr.foi.air.core.modularFunctionInterface.ILoyalityPointsUpdate;
import hr.foi.air.food2go.R;
import hr.foi.air.food2go.controller.Internet;

public class bodoviVjernostiFragment extends Fragment  {

    View view;
    ILoyalityPointsUpdate iLoyalityPointsUpdate;
    public boolean Modul;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = container;
        return inflater.inflate(R.layout.activity_loyalitypoints, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

       /* if (Internet.isNetworkAvailable(getContext()) == true) {
            RelativeLayout sifra = (RelativeLayout) view.findViewById(R.id.ostvariBodovePrekoSifre);
            RelativeLayout qrCode = (RelativeLayout) view.findViewById(R.id.ostvariBodovePrekoQrkoda);

            sifra.setOnClickListener(this);
            qrCode.setOnClickListener(this);
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
            alertDialog.setTitle("Pogreška u internet vezi");
            alertDialog.setMessage("Molimo Vas omogućite internetsku vezu kako biste koristili aplikaciju.");
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
/*
    }
/*

    public void onClick(View v) {
        switch (v.getId()) {

          case R.id.ostvariBodovePrekoSifre: {
                iLoyalityPointsUpdate = new LoyalityPontsWithCodeFragment();
                iLoyalityPointsUpdate.setData(Korisnik.getPrijavljeniKorisnik().getId(), "");
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, (Fragment) iLoyalityPointsUpdate)
                        .addToBackStack("BodoviFragment")
                        .commit();
            }
            break;
            case R.id.ostvariBodovePrekoQrkoda:

                break;
            default:
                break;
        }
    }


    @Override
    public void Update() {

        Racun racun = iLoyalityPointsUpdate.getData();
        String poruka= "Iskoristili ste kod računa s  brojem "+racun.getBrojRacuna()+" te ste ostvarili određeni broj bodova vjernosti !";
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("Azurirani bodovi");
        alertDialog.setMessage(poruka);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }*/
    }}
