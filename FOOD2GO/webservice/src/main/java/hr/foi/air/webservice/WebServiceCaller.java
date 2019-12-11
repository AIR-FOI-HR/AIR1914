package hr.foi.air.webservice;

import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hr.foi.air.core.Artikl;
import hr.foi.air.core.Korisnik;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

    public void CallDohvatiArtiklePoKategoriji(String kategorija){
        WebService webService = retrofit.create(WebService.class);
        call = webService.DohvatiArtiklePoKategoriji(kategorija);
        HandleResponseFromCall("DohvatiArtiklePoKategoriji");
    }

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
                            /*
            if(metoda==prijava){
                hendlajZaKorsinikametodu
            }
            * **/
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    private void HandleArtiklePoKategoriji(Response<WebServiceResponse> response){
        Gson gson = new Gson();
        Artikl[] artikli = gson.fromJson(response.body().getPodaci().toString(), Artikl[].class);
        webServiceHandler.onDataArrived(response.body().getPoruka(), response.body().getStatus(), Arrays.asList(artikli));
    }
}
