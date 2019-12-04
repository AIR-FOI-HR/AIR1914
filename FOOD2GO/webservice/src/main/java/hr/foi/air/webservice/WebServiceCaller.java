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

    public void CallForKorisnici(Korisnik data) {
        WebService webService = retrofit.create(WebService.class);
        call = webService.PrijaviSe(data.getUsername(),data.getLozinka());
        if (call != null) {
            call.enqueue(new Callback<WebServiceResponse>() {
                @Override
                public void onResponse(Response<WebServiceResponse> response, Retrofit retrofit) {
                    try {
                        if (response.isSuccess()) {
                            //if(method == "prijava") {
                                Log.i("Air",response.body().getPoruka());
                                HandlePojedinacanZapis(response);
                            //}
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
    private void HandlePojedinacanZapis(Response<WebServiceResponse> response){
        Gson gson = new Gson();

        Korisnik[] prijavljeni = gson.fromJson( response.body().getPodaci().toString(),Korisnik[].class);
        Log.i("SS",response.body().getPodaci().toString());
        if (webServiceHandler != null){
            webServiceHandler.onDataArrived(response.body().getPoruka(),response.body().getStatus(), Arrays.asList(prijavljeni) );
        }
    }
}
