package hr.foi.air.food2go.dataLoaders;

import com.example.webservice.WebServiceCaller;
import com.example.webservice.WebServiceHandler;

public class WsDataLoader {
    private DataLoadedListener dataLoadedListener;
    private WebServiceCaller webServiceCaller;
    WebServiceHandler webServiceHandler = new WebServiceHandler() {
        @Override
        public void onDataArrived(String message, String status, Object data) {
            dataLoadedListener.onDataLoaded(message,status,data);
        }

    };
    public WsDataLoader() {
        webServiceCaller = new WebServiceCaller(webServiceHandler);
    }

    /*
    Metode za poziv web servis callera
    metoda=prijava;
     public void Prijava(Korisnik korisnik,DataLoadedListener dataLoadedListener, string metoda){
        this.dataLoadedListener=dataLoadedListener;
        webServiceCaller.CallForKorisnici(korisnik, metoda);

    }
    * **/

}