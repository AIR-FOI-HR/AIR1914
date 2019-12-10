package hr.foi.air.food2go.fragmenti.kategorije;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import hr.foi.air.food2go.R;
import hr.foi.air.food2go.fragmenti.moje_narudzbe.MojeNarudzbeViewModel;

public class KategorijeFragment extends Fragment {

    private KategorijeViewModel kategorijeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        kategorijeViewModel =
                ViewModelProviders.of(this).get(KategorijeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_kategorije, container, false);
        final TextView textView = root.findViewById(R.id.text_kategorije);
        kategorijeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}