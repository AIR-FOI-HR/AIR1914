package hr.foi.air.food2go.controller.dataLoaders;

import hr.foi.air.core.BodoviVjernostiView;
import hr.foi.air.core.Korisnik;
import hr.foi.air.food2go.controller.dataLoaders.DataLoadedListener;
import hr.foi.air.webservice.WebServiceCaller;
import hr.foi.air.webservice.WebServiceHandler;

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

    public void Registracija(Korisnik korisnik, DataLoadedListener dataLoadedListener){
        this.dataLoadedListener = dataLoadedListener;
        webServiceCaller.CallForKorisnici(korisnik, "registracija");
    }

    public void Aktivacija(Korisnik korisnik, DataLoadedListener dataLoadedListener) {
        this.dataLoadedListener = dataLoadedListener;
        webServiceCaller.CallForKorisnici(korisnik, "aktivacijski");
    }

     public void Prijava(Korisnik korisnik, DataLoadedListener dataLoadedListener){
        this.dataLoadedListener = dataLoadedListener;
        webServiceCaller.CallForKorisnici(korisnik, "prijava");
    }

    public void ZaboravljenaLozinka(Korisnik korisnik, DataLoadedListener dataLoadedListener) {
        this.dataLoadedListener = dataLoadedListener;
        webServiceCaller.CallForKorisnici(korisnik, "zaboravljenalozinka");
    }

    public void AzurirajKorisnika(Korisnik korisnik, DataLoadedListener dataLoadedListener){
        this.dataLoadedListener = dataLoadedListener;
        webServiceCaller.CallForKorisnici(korisnik, "azurirajKorisnika");
    }
    public void DohvatiArtiklePoKategoriji(DataLoadedListener dataLoadedListener, String kategorija){
        this.dataLoadedListener = dataLoadedListener;
        webServiceCaller.CallDohvatiArtiklePoKategoriji(kategorija);
    }
    public void DohvatiArtikleTrenutneNarudzbe(DataLoadedListener dataLoadedListener,Integer racun_id){
        this.dataLoadedListener= dataLoadedListener;
        webServiceCaller.CallDohvatiArtikleTrenutneNarudzbe(racun_id);
    }
    public void IskoristiBodoveVjernosti(Korisnik korisnik){
        this.dataLoadedListener= dataLoadedListener;
        webServiceCaller.IskoristiBodove(korisnik);
    }

    public void ZabiljeziBodoveVjernosti(Korisnik korisnik, BodoviVjernostiView bodoviVjernostiView){
        this.dataLoadedListener= dataLoadedListener;
        webServiceCaller.ZabiljeziBodove(korisnik,bodoviVjernostiView);
    }

    public void KreirajRacun(Korisnik korisnik){
        this.dataLoadedListener=dataLoadedListener;
        webServiceCaller.KreirajNoviRacun(korisnik);
    }
}
