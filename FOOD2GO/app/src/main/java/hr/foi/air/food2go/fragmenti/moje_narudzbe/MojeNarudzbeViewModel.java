package hr.foi.air.food2go.fragmenti.moje_narudzbe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MojeNarudzbeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MojeNarudzbeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}