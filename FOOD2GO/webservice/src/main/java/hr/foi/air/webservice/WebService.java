package hr.foi.air.webservice;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface WebService {
    @GET("prijava/{username}/{password}/")
    Call<WebServiceResponse> PrijaviSe (@Path("username") String username, @Path("password") String password);
}
