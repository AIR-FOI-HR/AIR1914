package hr.foi.air.food2go.fragmenti.kategorije;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import hr.foi.air.food2go.GlavniActivity;
import hr.foi.air.food2go.R;
import hr.foi.air.food2go.controller.Internet;
import hr.foi.air.food2go.fragmenti.odabir_kategorije.OdabirKategorije;

public class KategorijeViewModel extends Fragment implements View.OnClickListener {

    public static String Kategorija;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kategorije, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RelativeLayout hrana = (RelativeLayout) view.findViewById(R.id.hranaKategorija);
        RelativeLayout pice = (RelativeLayout) view.findViewById(R.id.piceKategorija);

        hrana.setOnClickListener(this);
        pice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hranaKategorija:
                KategorijeViewModel.Kategorija = "1";
                break;
            case R.id.piceKategorija:
                KategorijeViewModel.Kategorija = "2";
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