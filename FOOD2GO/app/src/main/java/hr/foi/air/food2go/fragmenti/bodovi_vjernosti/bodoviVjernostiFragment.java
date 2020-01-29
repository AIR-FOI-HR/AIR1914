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

import com.example.qrcodeloyalitypointsupdate.Fragmenti.QRCodeFragmenti.QRCodeFragment;

import hr.foi.air.core.Korisnik;
import hr.foi.air.core.modularFunctionInterface.ILoyalityPointsUpdate;
import hr.foi.air.food2go.R;
import hr.foi.air.food2go.controller.Internet;
import hr.foi.air.food2go.fragmenti.odabir_kategorije.OdabirKategorije;

public class bodoviVjernostiFragment extends Fragment implements View.OnClickListener{

    ILoyalityPointsUpdate iLoyalityPointsUpdate;
    View view;
    public boolean Modul;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = container;
        return inflater.inflate(R.layout.fragment_loyalitypoints, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        if (Internet.isNetworkAvailable(getContext()) == true) {
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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ostvariBodovePrekoSifre:
 
                break;
            case R.id.ostvariBodovePrekoQrkoda:
                iLoyalityPointsUpdate = new QRCodeFragment();
                iLoyalityPointsUpdate.setData(Korisnik.getPrijavljeniKorisnik().getId(),"");
                FragmentManager fragmentManager;
                fragmentManager= getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment,(Fragment)iLoyalityPointsUpdate).addToBackStack(null);
                fragmentTransaction.commit();


                break;
            default:
                break;
        }
    }
}
