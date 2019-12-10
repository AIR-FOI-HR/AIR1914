package hr.foi.air.webservice;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface WebService {
    @GET("artikli/{kategorija}/")
    Call<WebServiceResponse> DohvatiArtiklePoKategoriji (@Path("kategorija") String kategorija);
}
