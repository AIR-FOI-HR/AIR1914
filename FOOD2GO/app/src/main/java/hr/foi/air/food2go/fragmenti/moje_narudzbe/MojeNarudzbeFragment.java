package hr.foi.air.food2go.fragmenti.moje_narudzbe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import hr.foi.air.food2go.R;

public class MojeNarudzbeFragment extends Fragment {

    private MojeNarudzbeViewModel mojeNarudzbeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mojeNarudzbeViewModel =
                ViewModelProviders.of(this).get(MojeNarudzbeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_moje_narudzbe, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        mojeNarudzbeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}