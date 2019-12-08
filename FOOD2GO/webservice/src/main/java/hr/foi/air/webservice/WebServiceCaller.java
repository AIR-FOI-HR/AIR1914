package hr.foi.air.webservice;

import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;

import java.util.Arrays;

import hr.foi.air.core.Korisnik;
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
        else if(method == "aktivacijski"){
            call = webService.AktivacijskiKod(data.getUsername(), data.getLozinka());
        }
        if (call != null) {
            call.enqueue(new Callback<WebServiceResponse>() {
                @Override
                public void onResponse(Response<WebServiceResponse> response, Retrofit retrofit) {
                    try {
                        if (response.isSuccess()) {
                            if(webServiceHandler!=null){
                            }
                               if(method == "registracija" || method == "aktivacijski"){
                                   HandlePojedinacanZapis(response);
                               }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                @Override
                public void onFailure(Throwable t) {
                    Log.e("SSSSSSSSSSSSS",t.getMessage());
                }
            });
        }
    }

    private void HandlePojedinacanZapis(Response<WebServiceResponse> response){
        Gson gson = new Gson();

        Korisnik[] registrirani = gson.fromJson( response.body().getPodaci().toString(),Korisnik[].class);
        Log.i("SSSSSSSSSSSSS",response.body().getPodaci().toString());
        if (webServiceHandler != null){
            webServiceHandler.onDataArrived(response.body().getPoruka(),response.body().getStatus(), Arrays.asList(registrirani) );
        }
    }
}
