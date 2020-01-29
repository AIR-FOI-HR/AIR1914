package com.example.qrcodeloyalitypointsupdate;

import hr.foi.air.webservice.WebServiceCaller;
import hr.foi.air.webservice.WebServiceHandler;

public class CodePointsLoyalityWebServices {
    OnCodeUpdate onCodeUpdate;
    WebServiceCaller webServiceCaller;
    WebServiceHandler webServiceHandler= new WebServiceHandler() {
        @Override
        public void onDataArrived(String message, String status, Object data) {
            onCodeUpdate.onDataLoaded(message,status,data);
        }
    };
    public CodePointsLoyalityWebServices(){
        webServiceCaller = new WebServiceCaller(webServiceHandler);
    }
    public void DohvatiRacun(int korisnikID,String kod,OnCodeUpdate dataLoadedListener){
        this.onCodeUpdate= dataLoadedListener;
        webServiceCaller.CallForRacunQR(korisnikID,kod);
    }
}
