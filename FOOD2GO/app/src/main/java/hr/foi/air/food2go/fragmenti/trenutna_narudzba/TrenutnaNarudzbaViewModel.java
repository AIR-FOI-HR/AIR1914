package hr.foi.air.food2go.fragmenti.trenutna_narudzba;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TrenutnaNarudzbaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TrenutnaNarudzbaViewModel() {
        mText = new MutableLiveData<>();
        ///KATEGORIJE
        mText.setValue("Trenutna narudzba");
    }
    public LiveData<String> getText() {
        return mText;
    }
}