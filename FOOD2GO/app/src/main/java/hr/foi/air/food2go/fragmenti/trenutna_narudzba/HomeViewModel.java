package hr.foi.air.food2go.fragmenti.trenutna_narudzba;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        ///KATEGORIJE
        mText.setValue("Glavni zaslon");
    }
    public LiveData<String> getText() {
        return mText;
    }
}