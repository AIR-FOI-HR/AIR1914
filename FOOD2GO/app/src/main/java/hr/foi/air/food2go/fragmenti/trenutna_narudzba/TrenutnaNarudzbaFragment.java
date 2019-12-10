package hr.foi.air.food2go.fragmenti.trenutna_narudzba;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TrenutnaNarudzbaFragment extends ViewModel {

    private MutableLiveData<String> mText;

    public TrenutnaNarudzbaFragment() {
        mText = new MutableLiveData<>();
        ///KATEGORIJE
        mText.setValue("Glavni zaslon");
    }
    public LiveData<String> getText() {
        return mText;
    }
}