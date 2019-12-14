package hr.foi.air.webservice;

import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;

import java.util.Arrays;

import hr.foi.air.core.Artikl;
import hr.foi.air.core.Korisnik;
import hr.foi.air.core.Racun;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class WebServiceCaller {
    Retrofit retrofit;
    WebServiceHandler webServiceHandler;
    Call<WebServiceResponse> call;
    private final String baseUrl = "https://airfood2go.000webhostapp.com/Servis/";

    public WebServiceCaller(WebServiceHandler webServiceHandler) {
        this.webServiceHandler = webServiceHandler;
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setProtocols(Arrays.asList(Protocol.HTTP_1_1));
        this.retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient).build();
    }

    public void CallForKorisnici(Korisnik data, final String method) {
        WebService webService = retrofit.create(WebService.class);
        if(method == "registracija"){
            call = webService.RegistrirajSe(data.getIme(), data.getPrezime(),
                    data.getUsername(), data.getLozinka(),
                    data.getOib(), data.getEmail(), data.getAdresa(),
                    data.getMobitel(), data.getAktivacijskiKod());
        }
        else if(method == "aktivacijski") {
            call = webService.AktivacijskiKod(data.getEmail(), data.getAktivacijskiKod());
        }
        else if(method == "prijava"){
            call = webService.PrijaviSe(data.getUsername(),data.getLozinka());
        }
        else if(method == "zaboravljenalozinka"){
            call = webService.ZaboravljenaLozinka(data.getLozinka(),data.getUsername());
        }
        else if (method=="azurirajKorisnika"){
            call = webService.AzurirajKorisnika(data.getIme(),data.getPrezime(),data.getUsername(),data.getAdresa(),data.getLozinka(),data.getMobitel(),data.getId(),data.getEmail());
        }
        CallFromServer(method);
    }

    public void CallDohvatiArtiklePoKategoriji(String kategorija){
        WebService webService = retrofit.create(WebService.class);
        call = webService.DohvatiArtiklePoKategoriji(kategorija);
        CallFromServer("DohvatiArtiklePoKategoriji");
    }

    public void CallDohvatiRacune(String korisnickoIme){
        WebService webService = retrofit.create(WebService.class);
        call = webService.DohvatiRacuneKorisnika(korisnickoIme);
        CallFromServer("dohvatiracunekorisnika");
    }

    private void CallFromServer(final String method){
        if (call != null) {
            call.enqueue(new Callback<WebServiceResponse>() {
                @Override
                public void onResponse(Response<WebServiceResponse> response, Retrofit retrofit) {
                    try {
                        if (response.isSuccess()) {
                            if(method == "prijava" || method == "zaboravljenalozinka" || method == "registracija" || method == "aktivacijski") {
                                HandlePojedinacanZapis(response);
                            }
                            else if(method == "dohvatiracunekorisnika"){
                                HandlePojedinacanRacun(response);
                            }
                            else if(method == "DohvatiArtiklePoKategoriji"){
                                HandleArtiklePoKategoriji(response);
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                @Override
                public void onFailure(Throwable t) {
                    Log.e("SS",t.getMessage());
                }
            });
        }
    }

    /*
    public void HandleResponseFromCall(final String method) {
        if (call != null) {
            call.enqueue(new Callback<WebServiceResponse>() {
                @Override
                public void onResponse(Response<WebServiceResponse> response, Retrofit retrofit) {
                    try {
                        if (response.isSuccess()) {
                            if(webServiceHandler!=null){
                                if(method == "DohvatiArtiklePoKategoriji"){
                                    HandleArtiklePoKategoriji(response);
                                }
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                @Override
                public void onFailure(Throwable t) {
                    Log.e("SS",t.getMessage());
                }
            });
        }
       }
     */

    private void HandlePojedinacanZapis(Response<WebServiceResponse> response){
        try{
            Gson gson = new Gson();
            Korisnik[] korisnik = gson.fromJson( gson.toJson(response.body().getPodaci()),Korisnik[].class);
            if (webServiceHandler != null){
                webServiceHandler.onDataArrived(response.body().getPoruka(),response.body().getStatus(), Arrays.asList(korisnik) );
            }
        }catch (Exception ex){
            ex.getMessage();
        }

    }

    private void HandlePojedinacanRacun(Response<WebServiceResponse> response){
        Gson gson = new Gson();
        Racun[] racuni = gson.fromJson(response.body().getPodaci().toString(), Racun[].class);
        webServiceHandler.onDataArrived(response.body().getPoruka(), response.body().getStatus(), Arrays.asList(racuni));
    }

    private void HandleArtiklePoKategoriji(Response<WebServiceResponse> response){
        Gson gson = new Gson();
        Artikl[] artikli = gson.fromJson(response.body().getPodaci().toString(), Artikl[].class);
        webServiceHandler.onDataArrived(response.body().getPoruka(), response.body().getStatus(), Arrays.asList(artikli));
    }
}
