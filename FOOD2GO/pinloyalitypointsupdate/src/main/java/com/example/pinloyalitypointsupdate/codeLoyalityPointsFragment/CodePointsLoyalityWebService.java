package com.example.pinloyalitypointsupdate.codeLoyalityPointsFragment;

import hr.foi.air.core.Korisnik;
import hr.foi.air.webservice.WebServiceCaller;
import hr.foi.air.webservice.WebServiceHandler;

public class CodePointsLoyalityWebService {
    OnCodeUpdate onCodeUpdate;
    WebServiceCaller webServiceCaller;
    WebServiceHandler webServiceHandler = new WebServiceHandler() {
        @Override
        public void onDataArrived(String message, String status, Object data) {
            onCodeUpdate.onDataLoaded(message,status,data);
        }

    };
    public CodePointsLoyalityWebService() {
        webServiceCaller = new WebServiceCaller(webServiceHandler);
    }
    public void DohvatiRacun(int korisnikID,String kod, OnCodeUpdate dataLoadedListener) {
        this.onCodeUpdate = dataLoadedListener;
       webServiceCaller.CallForRacun(korisnikID,kod);
    }

}
