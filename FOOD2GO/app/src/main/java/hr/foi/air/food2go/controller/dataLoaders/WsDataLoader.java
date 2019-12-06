package hr.foi.air.food2go.controller.dataLoaders;

import hr.foi.air.core.Korisnik;
import hr.foi.air.webservice.WebServiceCaller;
import hr.foi.air.webservice.WebServiceHandler;

public class WsDataLoader {
    private DataLoadedListener dataLoadedListener;
    private WebServiceCaller webServiceCaller;
    WebServiceHandler webServiceHandler = new WebServiceHandler() {
        @Override
        public void onDataArrived(String message, String status,Object data) {
            dataLoadedListener.onDataLoaded(message,status,data);
        }
    };

    public WsDataLoader() {
        webServiceCaller = new WebServiceCaller(webServiceHandler);
    }

    public void Prijava(Korisnik korisnik, DataLoadedListener dataLoadedListener) {
        this.dataLoadedListener = dataLoadedListener;
        webServiceCaller.CallForKorisnici(korisnik, "prijava");
    }
    }
}
