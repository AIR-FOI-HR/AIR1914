package hr.foi.air.food2go.fragmenti.kategorije;

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
import androidx.fragment.app.FragmentTransaction;

import hr.foi.air.food2go.GlavniActivity;
import hr.foi.air.food2go.R;
import hr.foi.air.food2go.controller.Internet;
import hr.foi.air.food2go.fragmenti.odabir_kategorije.OdabirKategorije;

public class KategorijeFragment extends Fragment implements View.OnClickListener {

    public static String Kategorija;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kategorije, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (Internet.isNetworkAvailable(getContext()) == true) {
            RelativeLayout hrana = (RelativeLayout) view.findViewById(R.id.hranaKategorija);
            RelativeLayout pice = (RelativeLayout) view.findViewById(R.id.piceKategorija);

            hrana.setOnClickListener(this);
            pice.setOnClickListener(this);
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
            case R.id.hranaKategorija:
                KategorijeFragment.Kategorija = "1";
                break;
            case R.id.piceKategorija:
                KategorijeFragment.Kategorija = "2";
                break;
            default:
                break;
        }
        OdabirKategorije odabirKategorije = new OdabirKategorije();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, odabirKategorije);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}